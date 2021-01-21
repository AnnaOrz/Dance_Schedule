package annas.dance_schedule.controllers;


import annas.dance_schedule.exceptions.UserAlreadyExistException;
import annas.dance_schedule.model.User;
import annas.dance_schedule.model.UserDto;
import annas.dance_schedule.repository.UserRepository;
import annas.dance_schedule.services.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class MainPageController {
    private UserService userService;
    private UserRepository userRepository;

    public MainPageController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("")
    public String goToMain(){
        return "mainPage";
    }


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "/registrationForm";
    }
    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute @Valid UserDto userDto, BindingResult result) throws UserAlreadyExistException {
        if (result.hasErrors()) {
            return "/registrationForm";
        } else {
            User user = userService.registerNewUserAccount(userDto);
            userRepository.save(user);
            return "dodałem nowego użytkownika";
        }
    }
    @RequestMapping("/schedule")
    public String goToSchedule(){
        return "/schedule";
    }
    @RequestMapping("/contact")
    public String contact(){
        return "/contact";
    }


}
