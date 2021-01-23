package annas.dance_schedule.controllers;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.CarnetType;
import annas.dance_schedule.model.MyUserPrincipal;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

import java.util.List;
import java.util.logging.Logger;


@Controller
@RequestMapping("/dance/carnets")
public class CarnetController {
    private final CarnetRepository carnetRepository;
    private final CarnetTypeRepository carnetTypeRepository;
    private final UserRepository userRepository;
    public Logger logger;

    @ModelAttribute("allCarnetTypes")
    public List<CarnetType> allCarnetTypes() {
        return carnetTypeRepository.findAll();
    }


    public CarnetController(CarnetRepository carnetRepository, CarnetTypeRepository carnetTypeRepository, UserRepository userRepository) {
        this.carnetRepository = carnetRepository;
        this.carnetTypeRepository = carnetTypeRepository;
        this.userRepository = userRepository;


    }



    @GetMapping("/buy")
    public String buyForm(Model model) {

        model.addAttribute("carnet", new Carnet());

        return "carnet/buy";
    }

    @PostMapping("/buy")
    @ResponseBody
    public String buyCarnet(@ModelAttribute Carnet carnet ) {
        if(carnet.getStartDate() == null){
            return "podaj datę rozpoczęcia karnetu";
        }
        if(carnet.getStartDate().isBefore(LocalDate.now())){
            return "data rozpoczęcia nie może być z przeszłości";
        }
        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = current.getUsername();
        if(userRepository.findByEmail(userName).isEmpty()){
            return "nie znalazłem aktywnego użytkownika";
        }
        User currentUser = userRepository.findByEmail(userName).get();
        CarnetType carnetType = carnetTypeRepository.getOne(carnet.getCarnetType().getId());
        carnet.setUser(currentUser);
        carnet.setAccessNumber(carnetType.getAccessNumber());
        carnet.setEntrances(carnetType.getEntrances());
        carnet.setPrice(carnetType.getPrice());
        carnet.setExpireDate(carnet.getStartDate().plusDays(30));

        carnetRepository.save(carnet);
     /*   currentUser.addCarnet(carnet);*/ //nie bo cascadeType mam na User i Carnet
        return "dodałem nowy karnet użytkownika";

    }

}
