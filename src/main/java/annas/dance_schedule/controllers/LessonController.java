package annas.dance_schedule.controllers;


import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/dance/lessons")
public class LessonController {


    @GetMapping("/add")
    public String goToAddLessonForm(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "/lesson/add";
    }

    @ResponseBody
    @PostMapping("/add")
    public String addLesson(@ModelAttribute @Valid Lesson lesson, BindingResult result) {

        return "dodałem nową lekcję";
    }
}