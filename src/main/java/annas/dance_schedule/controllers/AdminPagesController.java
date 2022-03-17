package annas.dance_schedule.controllers;

import annas.dance_schedule.model.*;
import annas.dance_schedule.repository.*;
import annas.dance_schedule.services.CarnetService;
import annas.dance_schedule.services.LessonService;
import annas.dance_schedule.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dance/admin")
public class AdminPagesController {

    private final LessonService lessonService;
    private final UserService userService;
    private final CarnetService carnetService;
    private final RoleRepository roleRepository;

    private final CarnetTypeRepository carnetTypeRepository;
    private final CarnetRepository carnetRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    // TODO: pozbyć się stąd repo!!! dostęp z poziomu serwisów, tylko!

    public AdminPagesController(CarnetTypeRepository carnetTypeRepository, CarnetRepository carnetRepository, UserRepository userRepository, LessonRepository lessonRepository, LessonService lessonService, UserService userService, CarnetService carnetService, RoleRepository roleRepository) {
        this.carnetTypeRepository = carnetTypeRepository;
        this.carnetRepository = carnetRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
        this.userService = userService;
        this.carnetService = carnetService;

        this.roleRepository = roleRepository;
    }


    @ModelAttribute("allCarnetTypes")
    public List<CarnetType> allCarnetTypes() {
        return carnetTypeRepository.findAll();
    }

    @ModelAttribute("allCarnets")
    public List<Carnet> allCarnets() {
        return carnetRepository.findAll();
    }

    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return userService.findAllUsers();
    }

    @ModelAttribute("allLessons")
    public List<Lesson> allLessons() {
        return lessonRepository.findAll();
    }

    @ModelAttribute("allTrainers")
    public List<User> allTrainers() {
        Optional<Role> trainer = roleRepository.findByName("ROLE_TRAINER");
        if(trainer.isPresent()){
            return userRepository.findAllByRoles(trainer.get());
        } else {
            System.out.println("Uwaga, nie znaleziono roli: ROLE_TRAINER");
            return new ArrayList<User>();
        }

    }


    @RequestMapping("")
    public String goToAdminMenu() {
        return "/admin/menu";
    }

    @RequestMapping("/carnetTypes")
    public String goToCarnetTypes(Model model) {
        model.addAttribute("carnetTypes", carnetTypeRepository.findAll());
        return "/admin/allCarnetTypes";
    }

    @GetMapping("/addCarnetType")
    public String addCarnetTypeGoToForm(Model model) {
        model.addAttribute("carnetType", new CarnetType());
        return "admin/addCarnetType";
    }


    @PostMapping("/addCarnetType")
    public String addCarnetType(@ModelAttribute @Valid CarnetType carnetType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/addCarnetType";
        } else {
            carnetType.setDescription(carnetType.getDescription()
                    + "\n" + " cena: " + carnetType.getPrice()
                    + "\n" + " ilość wejść: " + carnetType.getEntrances());
            carnetTypeRepository.save(carnetType);
            model.addAttribute("message", "zapisałem nowy typ karnetu");
            model.addAttribute("carnetTypes", carnetTypeRepository.findAll());
            return "redirect:/dance/admin/carnetTypes";
        }
    }

    @RequestMapping("/lessons")
    public String goToLessons() {
        lessonService.UpdateLessonsStatus();
        return "/admin/allLessons";
    }

    @GetMapping("/lessons/add")
    public String addLessonGoToForm(Model model) {
        model.addAttribute("lessonDto", new LessonDto());
        return "admin/addNewLesson";
    }

    @PostMapping("/lessons/add")
    public String addLesson(@ModelAttribute @Valid LessonDto lessonDto, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addNewLesson";
        }
        try{
        Lesson lesson = new Lesson();
        lesson.setState("active");
        lesson.setSlots(lessonDto.getSlots());
        lesson.setAccessNumber(lessonDto.getAccessNumber());
        lesson.setBeginTime(lessonDto.getBeginTime());
        lesson.setTrainer(userService.findUserById(lessonDto.getTrainerId()));
        lesson.setPlace(lessonDto.getPlace());
        lesson.setLevel(lessonDto.getLevel());
        lesson.setName(lessonDto.getName());
        lessonService.saveLesson(lesson);
        System.out.println("Zapisałem leckję");
        } catch (Exception ex) { ex.printStackTrace();}
        return "redirect:/dance/admin/lessons";
    }

    @Transactional
    @RequestMapping("/lessons/cancel/{id:[0-9]+}")
    public String cancelLesson(@PathVariable Long id) {
        Lesson lesson = lessonRepository.getOne(id);
        lesson.setState("canceled");
        List<User> participants = lesson.getParticipants();
        for (User user : participants) {
            lessonRepository.deleteParticipant(user.getId(), lesson.getId());
            userService.addOneEntranceToUserProperCarnet(user, lesson);
        }

        return "redirect:/dance/admin/lessons";
    }

    @RequestMapping("/carnets")
    public String goToCarnets() {
        return "admin/allCarnets";
    }

    @RequestMapping("/users")
    public String goToUsers() {
        return "admin/allUsers";
    }

    @GetMapping("/users/edit/{id:[0-9]+}")
    public String editUserGoToForm(@PathVariable Long id, Model model) {
        if (userRepository.findById(id).isEmpty()) {
            model.addAttribute("message", "nie znalazłem takiego użytkownika");
            return "admin/allUsers";
        }
        model.addAttribute("user", userRepository.findById(id).get());
        List<String> enabled = Arrays.asList("true", "false");
        model.addAttribute("enabled", enabled);
        return "admin/editUser";
    }

    @PostMapping("/users/edit/{id:[0-9]+}")
    public String editUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/editUser";
        }
        if (!user.getPassword().equals(userRepository.getOne(user.getId()).getPassword())) {
            user.setPassword(userService.encodeUserPassword(user.getPassword()));
        }
        user.setRoles(userRepository.getOne(user.getId()).getRoles());
        // TODO role już nie są stringiem i nie interesuje nas ich zmiana w tym momencie,
        // zapis nie działa z powodu tego że rola jest podawana jako string a nie kolekcja w POST,
        // sugerowane: nowe DTO

        userService.updateUser(user);
        return "redirect:/dance/admin/users";
    }

    @GetMapping("/lessons/edit/{id:[0-9]+}")
    public String editLessonGoToForm(@PathVariable Long id, Model model) {
        if (lessonRepository.findById(id).isEmpty()) {
            model.addAttribute("message", "Nie znaleziono lekcji o wybranym id");
            return "admin/allLessons";
        }
        model.addAttribute("lesson", lessonRepository.findById(id).get());
        return "admin/editLesson";
    }

    @PostMapping("/lessons/edit/{id:[0-9]+}")
    public String editLesson(@ModelAttribute @Valid Lesson lesson, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/editLesson";
        }
        lessonService.update(lesson);
        return "redirect:/dance/admin/lessons";
    }

    @GetMapping("/carnetTypes/edit/{id:[0-9]+}")
    public String editCarnetTypeGoToForm(@PathVariable Long id, Model model) {
        if (carnetTypeRepository.findById(id).isPresent()) {
            model.addAttribute("carnetType", carnetTypeRepository.findById(id).get());
            return "admin/editCarnetType";
        } else {
            model.addAttribute("message", "niepoprawne dane do edycji");
            model.addAttribute("carnetTypes", carnetTypeRepository.findAll());
            return "admin/allCarnetTypes";
        }
    }

    @PostMapping("/carnetTypes/edit/{id:[0-9]+}")
    public String editCarnetType(@ModelAttribute @Valid CarnetType carnetType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/editCarnetType";
        }
        carnetService.update(carnetType);
        model.addAttribute("carnetTypes", carnetTypeRepository.findAll());
        return "redirect:/dance/admin/carnetTypes";
    }

    @RequestMapping("/carnetTypes/delete/{id:[0-9]+}")
    public String deleteCarnetType(@PathVariable Long id, Model model) {
        carnetTypeRepository.deleteById(id);
        model.addAttribute("message", "usunięto pomyślnie wybrany typ karnetu");
        model.addAttribute("carnetTypes", carnetTypeRepository.findAll());
        return "redirect:/dance/admin/carnetTypes";
    }

    @GetMapping("/carnets/edit/{id:[0-9]+}")
    public String editCarnetGoToForm(@PathVariable Long id, Model model) {
        if (carnetRepository.findById(id).isPresent()) {
            model.addAttribute("carnet", carnetRepository.findById(id).get());
            return "admin/editCarnet";
        } else {
            model.addAttribute("message", "niepoprawne dane do edycji");
            model.addAttribute("carnets", carnetRepository.findAll());
            return "admin/allCarnets";
        }
    }

    @PostMapping("/carnets/edit/{id:[0-9]+}")
    public String editCarnet(@ModelAttribute @Valid Carnet carnet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            return "admin/editCarnet";
        }
        carnetService.update(carnet);
        model.addAttribute("carnets", carnetRepository.findAll());
        return "redirect:/dance/admin/carnets";
    }


}
