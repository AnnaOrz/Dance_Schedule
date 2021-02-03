package annas.dance_schedule.controllers;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.CarnetType;
import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import annas.dance_schedule.services.CarnetService;
import annas.dance_schedule.services.LessonService;
import annas.dance_schedule.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dance/admin")
public class AdminPagesController {
    private final CarnetTypeRepository carnetTypeRepository;
    private final CarnetRepository carnetRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final LessonService lessonService;
    private final UserService userService;
    private final CarnetService carnetService;

    public AdminPagesController(CarnetTypeRepository carnetTypeRepository, CarnetRepository carnetRepository, UserRepository userRepository, LessonRepository lessonRepository, LessonService lessonService, UserService userService, CarnetService carnetService) {
        this.carnetTypeRepository = carnetTypeRepository;
        this.carnetRepository = carnetRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
        this.userService = userService;
        this.carnetService = carnetService;
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
        return userRepository.findAll();
    }

    @ModelAttribute("allLessons")
    public List<Lesson> allLessons() {
        return lessonRepository.findAll();
    }

    @ModelAttribute("allTrainers")
    public List<User> allTrainers() {
        return userRepository.findAllByRole("Trainer");
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
        model.addAttribute("carnet", new CarnetType());
        return "admin/addCarnetType";
    }



    @PostMapping("/addCarnetType")
    public String addCarnetType(@ModelAttribute @Valid CarnetType carnetType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/addCarnetType";
        } else {
            carnetType.setDescription(carnetType.getDescription()
                    + " cena: " + carnetType.getPrice() + " ilość wejść: " + carnetType.getEntrances());
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
        model.addAttribute("lesson", new Lesson());
        return "admin/addNewLesson";
    }

    @PostMapping("/lessons/add")
    public String addLesson(@ModelAttribute @Valid Lesson lesson, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addNewLesson";
        }
        lessonRepository.save(lesson);
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
            userService.addOneEntranceToUserProperCarnet(user,lesson);
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
    public String editUserGoToForm(@PathVariable Long id, Model model){
        if(userRepository.findById(id).isEmpty()){
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
        if (result.hasErrors()) { return "admin/editUser"; }
        System.out.println(user.getRole());
        userService.update(user);
        return "redirect:/dance/admin/users";
    }
    @GetMapping("/lessons/edit/{id:[0-9]+}")
    public String editLessonGoToForm(@PathVariable Long id, Model model){
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
    public String editCarnetTypeGoToForm(@PathVariable Long id, Model model){
        if(carnetTypeRepository.findById(id).isPresent()) {
            model.addAttribute("carnetType", carnetTypeRepository.findById(id).get());
            return "admin/editCarnetType";
        } else {
            model.addAttribute("message", "niepoprawne dane do edycji");
            model.addAttribute("carnetTypes", carnetTypeRepository.findAll());
            model.addAttribute("boolean", Arrays.asList("true", "false"));
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
    public String deleteCarnetType(@PathVariable Long id, Model model){
        carnetTypeRepository.deleteById(id);
        model.addAttribute("message", "usunięto pomyślnie wybrany typ karnetu");
        model.addAttribute("carnetTypes", carnetTypeRepository.findAll());
        return "redirect:/dance/admin/carnetTypes";
    }


}
