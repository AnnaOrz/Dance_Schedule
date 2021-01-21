package annas.dance_schedule.controllers;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.CarnetType;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/dance/carnets")
public class CarnetController {
    private final CarnetRepository carnetRepository;
    private final CarnetTypeRepository carnetTypeRepository;

    public CarnetController(CarnetRepository carnetRepository, CarnetTypeRepository carnetTypeRepository) {
        this.carnetRepository = carnetRepository;
        this.carnetTypeRepository = carnetTypeRepository;
    }

    @GetMapping("/add")
    public String createForm(Model model){
        model.addAttribute("carnet", new CarnetType());
        return "carnet/add";

    }
    @ResponseBody
    @PostMapping("/add")
    public String createCarnet(@ModelAttribute @Valid CarnetType carnetType, BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().toString();
        } else {

            carnetTypeRepository.save(carnetType);
            return "zapisałem nowy typ karnetu";
        }
    }
    @GetMapping("/buy")
    public String buyForm(Model model) {
        model.addAttribute("carnet", new Carnet());
        return "carnet/buy";
    }
    @PostMapping("/buy")
    public String buyCarnet(@ModelAttribute @Valid Carnet carnet, BindingResult result){
        if (result.hasErrors()) {
            return result.getAllErrors().toString();
        } else {
            carnet.setExpireDate(carnet.getStartDate().plusDays(30));
           /* carnet.setUser();*/
            /*koniecznie dodanie użytkownika po id tego zalogowanego*/
            carnetRepository.save(carnet);
            return "dodałem nowy karnet";
        }

    }

}
