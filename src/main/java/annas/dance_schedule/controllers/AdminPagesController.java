package annas.dance_schedule.controllers;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.CarnetType;
import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import annas.dance_schedule.services.LessonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dance/admin")
public class AdminPagesController {
    private final CarnetTypeRepository carnetTypeRepository;
    private final CarnetRepository carnetRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final LessonService lessonService;

    public AdminPagesController(CarnetTypeRepository carnetTypeRepository, CarnetRepository carnetRepository, UserRepository userRepository, LessonRepository lessonRepository, LessonService lessonService) {
        this.carnetTypeRepository = carnetTypeRepository;
        this.carnetRepository = carnetRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
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
    public String goToCarnetTypes() {
        return "/admin/allCarnetTypes";
    }

    @GetMapping("/addCarnetType")
    public String addCarnetTypeGoToForm(Model model) {
        model.addAttribute("carnet", new CarnetType());
        return "carnet/add";
    }

    @PostMapping("/addCarnetType")
    public String addCarnetType(@ModelAttribute @Valid CarnetType carnetType, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/carnet/add";
        } else {
            carnetType.setDescription(carnetType.getDescription()
                    + " cena: " + carnetType.getPrice() + " ilość wejść: " + carnetType.getEntrances());
            carnetTypeRepository.save(carnetType);
            String message = "zapisałem nowy typ karnetu";
            request.setAttribute("message", message);
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
        return "lesson/add";
    }

    @PostMapping("/lessons/add")
    public String addLesson(@ModelAttribute @Valid Lesson lesson, BindingResult result) {
        if (result.hasErrors()) {
            return "/lesson/add";
        }
        //czy sprawdzać czy lekcja jest unikalna? albo np miejsce dwóch lekcji i ich czas się nie pokrywają?
        lessonRepository.save(lesson);
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
        model.addAttribute("user", userRepository.findById(id));
        return "admin/editUser";

    }
    @GetMapping("/lessons/edit/{id:[0-9]+}")
    public String editLessonGoToForm(@PathVariable Long id, Model model){
        model.addAttribute("lesson", lessonRepository.findById(id));
        return "admin/editLesson";
    }
    @GetMapping("/carnetTypes/edit/{id:[0-9]+}")
    public String editCarnetTypeGoToForm(@PathVariable Long id, Model model){
        model.addAttribute("carnetType", carnetTypeRepository.findById(id));
        return "admin/editCarnetType";
    }


}
