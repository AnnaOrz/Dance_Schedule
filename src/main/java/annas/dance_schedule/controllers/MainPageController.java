package annas.dance_schedule.controllers;


import annas.dance_schedule.exceptions.UserAlreadyExistException;
import annas.dance_schedule.model.User;
import annas.dance_schedule.model.UserDto;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import annas.dance_schedule.services.LessonService;
import annas.dance_schedule.services.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Controller
@RequestMapping("/")
public class MainPageController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final LessonService lessonService;

    public MainPageController(UserService userService, UserRepository userRepository, LessonRepository lessonRepository, LessonService lessonService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
    }
    @ResponseBody
    @RequestMapping("/denied")
    public String denied(){
        return "Nie masz dostępu do tej strony";
    }

    @RequestMapping("")
    public String goToMain(){
        return "mainPage";
    }


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/registrationForm";
    }
    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute @Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/registrationForm";
        } else {
            try {
                User user = userService.registerNewUserAccount(userDto);
                userRepository.save(user);
                return "mainPage";
            } catch (UserAlreadyExistException e) {
                model.addAttribute("message", "Taki mail jest już zarejestrowany");
                return "user/RegistrationForm";
            }
        }
    }
    @GetMapping("/schedule")
    public String goToSchedule(Model model){
        lessonService.UpdateLessonsStatus();
        model.addAttribute("classes", lessonRepository.findLessonsByBeginTimeAfter(LocalDate.now().atStartOfDay()));
        return "/schedule";
    }
    @PostMapping("/schedule")
    public String goToScheduleSearch(Model model, HttpServletRequest request){
        lessonService.UpdateLessonsStatus();
        try{
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        model.addAttribute("classes", lessonRepository.findLessonsByBeginTimeBetween(date.atStartOfDay(), date.atTime(23,59)));
        System.out.println(lessonRepository.findLessonsByBeginTimeBetween(date.atStartOfDay(), date.atTime(23,59)).size());
        return "/schedule";
        } catch (DateTimeParseException dtE)  {
            return "redirect:/schedule";

        }
    }
    @RequestMapping("/contact")
    public String contact(){
        return "/contact";
    }


}
