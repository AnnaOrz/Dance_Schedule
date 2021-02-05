package annas.dance_schedule.controllers;

import annas.dance_schedule.model.*;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import annas.dance_schedule.services.CarnetService;
import annas.dance_schedule.services.LessonService;
import annas.dance_schedule.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/dance/user")
public class UserController {
    private final CarnetRepository carnetRepository;
    private final LessonRepository lessonRepository;
    private final CarnetTypeRepository carnetTypeRepository;
    private final UserRepository userRepository;
    private final LessonService lessonService;
    private final CarnetService carnetService;
    private final UserService userService;
    private final Logger logger = Logger.getLogger("UserController");

    public User getCurrentUser() {
        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.findByEmail(current.getUsername()).isPresent()) {
            return userRepository.findByEmail(current.getUsername()).get();
        } else {
            return null;
        }


    }

    public UserController(CarnetRepository carnetRepository, LessonRepository lessonRepository, CarnetTypeRepository carnetTypeRepository, UserRepository userRepository, LessonService lessonService, CarnetService carnetService, UserService userService) {
        this.carnetRepository = carnetRepository;
        this.lessonRepository = lessonRepository;
        this.carnetTypeRepository = carnetTypeRepository;
        this.userRepository = userRepository;
        this.lessonService = lessonService;
        this.carnetService = carnetService;
        this.userService = userService;
    }

    @ModelAttribute("allCarnetTypes")
    public List<CarnetType> allCarnetTypes() {
        return carnetTypeRepository.findAll();
    }

    @ModelAttribute("allAvailableCarnetTypes")
    public List<CarnetType> allAvailableCarnetTypes() {
        return carnetTypeRepository.findAllAvailable();
    }

    @RequestMapping("/carnets")
    public String userCarnets(Model model) {
        model.addAttribute("userActiveCarnets", carnetRepository.findAllByUserAndExpireDateAfter(getCurrentUser(), LocalDate.now().minusDays(1)));
        model.addAttribute("userFormerCarnets", carnetRepository.findAllByUserAndExpireDateBefore(getCurrentUser(), LocalDate.now()));
        return "user/carnets";
    }

    @GetMapping("/data/edit/{id:[0-9]+}")
    public String userEditHisDataGoToForm(@PathVariable Long id, Model model) {
        if (getCurrentUser().getId().equals(id)) {
            model.addAttribute("user", getCurrentUser());
            return "user/editUser";
        } else {
            model.addAttribute("message", "nie masz dostępu do tych danych");
            model.addAttribute("user", getCurrentUser());
            return "user/accountData";
        }
    }

    @PostMapping("/data/edit/{id:[0-9]+}")
    public String userEditHisData(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            return "user/editUser";
        }
        if(!user.getPassword().equals(userRepository.getOne(user.getId()).getPassword())){
            user.setPassword(userService.EncodeUserPassword(user.getPassword()));
        }
            userService.update(user);
        return "user/accountData";
    }

    @RequestMapping("/data")
    public String showUserData(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "user/accountData";
    }


    @RequestMapping("/classes")
    public String userClasses(Model model) {
        User currentUser = getCurrentUser();
        List<Lesson> userLessonsApproaching = lessonRepository.findLessonsByBeginTimeAfter(LocalDateTime.now()).stream()
                .filter(lesson -> lesson.getParticipants().contains(currentUser))
                .collect(Collectors.toList());
        List<Lesson> userLessonsPassed = lessonRepository.findLessonsByBeginTimeBefore(LocalDateTime.now()).stream()
                .filter(lesson -> lesson.getParticipants().contains(currentUser))
                .collect(Collectors.toList());
        model.addAttribute("userClasses", userLessonsApproaching);
        model.addAttribute("userPassedClasses", userLessonsPassed);
        return "user/classes";

    }

    @GetMapping("/buy")
    public String buyForm(Model model) {

        model.addAttribute("carnetDto", new CarnetDto());

        return "user/buyCarnet";
    }

    @PostMapping("/buy")
    public String buyCarnet(@ModelAttribute @Valid CarnetDto carnetDto, BindingResult result) {
        if (result.hasErrors()) {
            return "user/buyCarnet";
        }

        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = current.getUsername();
        if (userRepository.findByEmail(userName).isEmpty()) {
            logger.log(Level.INFO, "nie znalazłem aktywnego użytkownika");
            return "/";
        }
        User currentUser = userRepository.findByEmail(userName).get();

        Carnet carnet = new Carnet();

        CarnetType carnetType = carnetTypeRepository.getOne(carnetDto.getCarnetTypeId());
        carnet.setUser(currentUser);
        carnet.setAccessNumber(carnetType.getAccessNumber());
        carnet.setEntrances(carnetType.getEntrances());
        carnet.setPrice(carnetType.getPrice());
        carnet.setStartDate(carnetDto.getStartDate());
        carnet.setExpireDate(carnetDto.getStartDate().plusDays(30));

        carnetRepository.save(carnet);
        return "redirect:/dance/user/carnets";

    }

    @RequestMapping("/signUp/{id:[0-9]+}")
    @Transactional
    public String signUpForLesson(@PathVariable Long id, Model model) {
        User user = getCurrentUser();
        if (lessonRepository.findById(id).isEmpty()) {
            model.addAttribute("message", "nie znaleziono takiej lekcji");
            return "/schedule";
        }
        Lesson lesson = lessonRepository.findById(id).get();
        if (!lesson.getState().equals("active")) {
            model.addAttribute("message", "Nie możesz już zapisać się na tę lekcję");
            return "/schedule";
        }
        if (lessonRepository.findLessonsByParticipantsIsContaining(user).contains(lesson)) {
            model.addAttribute("message", "Na dane zajęcia możesz zapisać się tylko raz");
            return "/schedule";
        }
        List<Carnet> userProperCarnets = user.getCarnets().stream()
                .filter(carnet -> carnet.getExpireDate().isAfter(lesson.getBeginTime().toLocalDate()))
                .filter(carnet -> carnet.getEntrances() > 0)
                .filter(carnet -> carnet.getAccessNumber().equals(lesson.getAccessNumber()))
                .sorted(Comparator.comparing(Carnet::getExpireDate))
                .collect(Collectors.toList());
        if (userProperCarnets.size() == 0) {
            model.addAttribute("message", "brak właściwego karnetu");
            return "/schedule";
        }
        Carnet carnet = userProperCarnets.get(0);
        carnet.setEntrances(carnet.getEntrances() - 1);
        lessonService.signUpForLesson(user.getEmail(), id);
        if (lesson.getParticipants().size() >= lesson.getSlots()) {
            model.addAttribute("message", "Zapisano na listę rezerwową" + lesson.getName());
            return "/schedule";
        } else {
            model.addAttribute("message", "Zapisano na zajęcia:  " + lesson.getName());
        }
        carnetService.update(carnet);

        return "redirect:/schedule";

    }

    @Transactional
    @RequestMapping("/optOut/{id:[0-9]+}")
    public String optOutOfLesson(@PathVariable Long id, Model model) {
        User user = getCurrentUser();
        if (lessonRepository.findById(id).isEmpty()) {
            model.addAttribute("message", "nie znaleziono takiej lekcji");
            return "/schedule";
        }

        Lesson lesson = lessonRepository.findById(id).get();
        if (!lessonRepository.findLessonsByParticipantsIsContaining(user).contains(lesson)) {
            model.addAttribute("message", "już wcześniej wypisano Cię z tej lekcji");
            return "/schedule";
        }

        if (lesson.getBeginTime().toLocalDate().isAfter(LocalDate.now())) {
            boolean entranceRestored = userService.addOneEntranceToUserProperCarnet(user, lesson);
            if (!entranceRestored) {
                model.addAttribute("message", "Nie znaleziono karnetu do zwrócenia wejścia");
            }
        }
        lessonRepository.deleteParticipant(user.getId(), lesson.getId());
        List<Lesson> userClasses = user.getClassesParticipating();
        userClasses.remove(lesson);
        user.setClassesParticipating(userClasses);
        model.addAttribute("userClasses", userClasses);

        return "/user/classes";

    }

    @PreAuthorize("hasAuthority('TRAINER')")
    @GetMapping("/users/activate/{id:[0-9]+}")
    public String ActivateUser(@PathVariable Long id, Model model) {
        if (userRepository.findById(id).isEmpty()) {
            model.addAttribute("message", "nie znaleziono takiego użytkownika");
            return "/schedule";
        }
        User user = userRepository.findById(id).get();
        userService.activateUser(user);
        return "/dance/user";
    }
}