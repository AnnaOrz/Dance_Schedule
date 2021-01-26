package annas.dance_schedule.controllers;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.CarnetType;
import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("/dance/admin")
public class AdminPagesController {
private final CarnetTypeRepository carnetTypeRepository;
private final CarnetRepository carnetRepository;
private final UserRepository userRepository;
private final LessonRepository lessonRepository;

    public AdminPagesController(CarnetTypeRepository carnetTypeRepository, CarnetRepository carnetRepository, UserRepository userRepository, LessonRepository lessonRepository) {
        this.carnetTypeRepository = carnetTypeRepository;
        this.carnetRepository = carnetRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
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
    public List<User> allUsers() { return userRepository.findAll();}
    @ModelAttribute("allLessons")
    public List<Lesson> allLessons() {return lessonRepository.findAll();}

    @RequestMapping("")
    public String goToAdminMenu(){
        return "/admin/menu";
    }

    @RequestMapping("/CarnetTypes")
    public String goToCarnetTypes() {return "/admin/allCarnetTypes";}

    @GetMapping("/addCarnetType")
    public String createForm(Model model) {
        model.addAttribute("carnet", new CarnetType());
        return "carnet/add";
    }

    @PostMapping("/addCarnetType")
    public String createCarnet(@ModelAttribute @Valid CarnetType carnetType, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "redirect:/dance/admin/addCarnetType";
        } else {
            carnetType.setDescription(carnetType.getDescription()
                    + " cena: " + carnetType.getPrice() + " ilość wejść: " + carnetType.getEntrances() );
            carnetTypeRepository.save(carnetType);
            String message = "zapisałem nowy typ karnetu";
            request.setAttribute("message", message);
            return "/admin/allCarnetTypes" ;
        }
    }


}
