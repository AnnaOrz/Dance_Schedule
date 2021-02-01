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
import java.util.NoSuchElementException;
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
    public List<CarnetType> allAvailableCarnetTypes() {return carnetTypeRepository.findAllAvailable();}

    @RequestMapping("/carnets")
    public String userCarnets(Model model){
        List<Carnet> allUserCarnets = carnetRepository.findAllByUserId(getCurrentUser().getId());
        model.addAttribute("allUserCarnets", allUserCarnets);
        return "user/carnets";
    }
    @RequestMapping("/classes")
    public String userClasses(Model model){
        List<Lesson> userLessonsApproaching = lessonRepository.findLessonsByBeginTimeAfter(LocalDateTime.now());
        List<Lesson> userLessonsPassed = lessonRepository.findLessonsByBeginTimeBefore(LocalDateTime.now());
        model.addAttribute("userClasses", userLessonsApproaching);
        model.addAttribute("userPassedClasses", userLessonsPassed);
        return "user/classes";

    }
    @GetMapping("/buy")
    public String buyForm(Model model) {

        model.addAttribute("carnetDto", new CarnetDto());

        return "carnet/buy";
    }

    @PostMapping("/buy")
    public String buyCarnet(@ModelAttribute @Valid CarnetDto carnetDto , BindingResult result) {
        if (result.hasErrors()) {
            return "carnet/buy";
        }

        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = current.getUsername();
        if(userRepository.findByEmail(userName).isEmpty()){
            return "nie znalazłem aktywnego użytkownika";
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
        return "/mainPage";

    }
    @RequestMapping("/signUp/{id:[0-9]+}")
    @Transactional
    public String signUpForLesson(@PathVariable Long id, Model model){
        User user = getCurrentUser();
        if(lessonRepository.findById(id).isEmpty()){
            model.addAttribute("message", "nie znaleziono takiej lekcji");
            return "/schedule";
        }
        Lesson lesson = lessonRepository.findById(id).get();
        List<Carnet> userProperCarnets = user.getCarnets().stream()
                .filter(carnet -> carnet.getExpireDate().isAfter(lesson.getBeginTime().toLocalDate()))
                .filter(carnet -> carnet.getEntrances() > 0)
                .filter(carnet -> carnet.getAccessNumber() >= lesson.getAccessNumber()) //compare Integers
                .sorted(Comparator.comparing(Carnet::getExpireDate)) //carnet o najdłuższej ważności na końcu
                .collect(Collectors.toList());
        if(userProperCarnets.size() == 0) {
            model.addAttribute("message", "brak właściwego karnetu");
            return "/schedule";
        }
            Carnet carnet = userProperCarnets.get(0);
            carnet.setEntrances(carnet.getEntrances()-1); //zabrać jedno wejście
            lessonService.signUpForLesson(user.getEmail(), id);
            if(lesson.getParticipants().size()>= lesson.getSlots()) {
                List<User> reserveList = lesson.getReserveList();
                reserveList.add(user);
                lesson.setReserveList(reserveList);
                model.addAttribute("message", "zapisano na listę rezerwową");
                return "/schedule";
            } else {
                model.addAttribute("message", "zapisano na zajęcia " + lesson.getName());
            }
            lessonService.update(lesson);
            carnetService.update(carnet);

        return "/schedule";

    }
    @RequestMapping("/optOut/{id:[0-9]+}")
    public String optOutOfLesson(@PathVariable Long id, Model model){
        User user = getCurrentUser();
        if(lessonRepository.findById(id).isEmpty()){
            model.addAttribute("message", "nie znaleziono takiej lekcji");
            return "/schedule";
        }
        Lesson lesson = lessonRepository.findById(id).get();
        if(lesson.getBeginTime().toLocalDate().isAfter(LocalDate.now())) {
            List<Carnet> userProperCarnets = user.getCarnets().stream()
                    .filter(carnet -> carnet.getExpireDate().isAfter(lesson.getBeginTime().toLocalDate()))
                    .filter(carnet -> carnet.getEntrances() > 0)
                    .filter(carnet -> carnet.getAccessNumber() >= lesson.getAccessNumber()) //compare Integers
                    .sorted(Comparator.comparing(Carnet::getExpireDate)) //carnet o najdłuższej ważności na końcu
                    .collect(Collectors.toList());
            if (userProperCarnets.size() == 0) {
                model.addAttribute("message", "brak właściwego karnetu");
                return "/schedule";
            }
            Carnet carnet = userProperCarnets.get(userProperCarnets.size() - 1);
            carnet.setEntrances(carnet.getEntrances() + 1);
        } //dodaj jedno wejście do karnetu użytkownika gdy data lekcji następuje po dzisiejszej
            List<User> lessonParticipants = lesson.getParticipants();
            lessonParticipants.remove(user);
            List<User> reserveList = lesson.getReserveList();
            if(reserveList.size()>0){ //jeśli lekcja ma listę rezerwową to weź pierwszego z listy rezerwowej i przenieś do participants
                User user2 = reserveList.get(0);
                lessonParticipants.add(user2);
                reserveList.remove(0);
            }
            lessonService.updateParticipants(lesson);
            return "/schedule";




        }





    @PreAuthorize("hasAuthority('TRAINER')")
    @GetMapping("/users/activate/{id:[0-9]+}")
    public String ActivateUser(@PathVariable Long id) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow();
        userService.activateUser(user);
        return "udało się";

    }



}
