package annas.dance_schedule.controllers;

import annas.dance_schedule.model.*;
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

    @ModelAttribute("allAvailableCarnetTypes")
    public List<CarnetType> allAvailableCarnetTypes() {return carnetTypeRepository.findAllAvailable();}


    public CarnetController(CarnetRepository carnetRepository, CarnetTypeRepository carnetTypeRepository, UserRepository userRepository) {
        this.carnetRepository = carnetRepository;
        this.carnetTypeRepository = carnetTypeRepository;
        this.userRepository = userRepository;


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
        carnet.setExpireDate(carnetDto.getStartDate().plusDays(30));

        carnetRepository.save(carnet);
        return "user/main";

    }

}
