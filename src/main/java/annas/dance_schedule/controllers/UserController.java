package annas.dance_schedule.controllers;

import annas.dance_schedule.model.*;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import annas.dance_schedule.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/dance/user")
public class UserController {
    private final CarnetRepository carnetRepository;
    private final LessonRepository lessonRepository;
    private final CarnetTypeRepository carnetTypeRepository;
    private final UserRepository userRepository;

    public User getCurrentUser() {
        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.findByEmail(current.getUsername()).isPresent()) {
            return userRepository.findByEmail(current.getUsername()).get();
        } else {
            return null;
        }


    }

    public UserController(CarnetRepository carnetRepository, LessonRepository lessonRepository, CarnetTypeRepository carnetTypeRepository, UserRepository userRepository) {
        this.carnetRepository = carnetRepository;
        this.lessonRepository = lessonRepository;
        this.carnetTypeRepository = carnetTypeRepository;
        this.userRepository = userRepository;
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


}
