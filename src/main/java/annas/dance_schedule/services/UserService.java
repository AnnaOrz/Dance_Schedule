package annas.dance_schedule.services;

import annas.dance_schedule.exceptions.UserAlreadyExistException;
import annas.dance_schedule.exceptions.UserNotFoundException;
import annas.dance_schedule.model.*;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CarnetService carnetService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CarnetService carnetService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.carnetService = carnetService;
    }

    @Transactional
    public User registerNewUserAccount(UserRegistrationDto userRegistrationDto)
            throws UserAlreadyExistException {

        if (isEmailAlreadyInUse(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            + userRegistrationDto.getEmail());
        } else {
            User user = new User();
            user.setEmail(userRegistrationDto.getEmail());
            user.setFirstName(userRegistrationDto.getFirstName());
            user.setLastName(userRegistrationDto.getLastName());
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setEnabled(false);
            Collection<Role> userRoles = new LinkedList<>();
            userRoles.add(new Role("USER"));
            user.setRoles(userRoles);
            return user;
        }
    }

    private boolean isEmailAlreadyInUse(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void updateUser(User user) {
        Optional<User> oldUser = userRepository.findById(user.getId());
        if (oldUser.isPresent()) {
            userRepository.updateUser(
                    user.getEmail(),
                    user.isEnabled(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    user.getId());
        }
    }



    public void activateUser(User user) {
        Collection<Role> userRoles = new LinkedList<>();
        userRoles.add(new Role("ROLE_USER"));
        user.setRoles(userRoles);
        user.setEnabled(true);
        updateUser(user);
    }
    public User findUserById(Long id) throws UserNotFoundException{
        Optional <User> user =  userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        } else throw new UserNotFoundException();
    }
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
    public List<User> findAllWithRole(Role role){
        return userRepository.findAllByRoles(role);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public boolean addOneEntranceToUserProperCarnet(User user, Lesson lesson) {
        List<Carnet> userProperCarnets = user.getCarnets().stream()
                .filter(carnet -> carnet.getExpireDate().isAfter(lesson.getBeginTime().toLocalDate()))
                .filter(carnet -> carnet.getEntrances() > 0)
                .filter(carnet -> carnet.getAccessNumber() >= lesson.getAccessNumber()) //compare Integers
                .sorted(Comparator.comparing(Carnet::getExpireDate)) //carnet o najdłuższej ważności na końcu
                .collect(Collectors.toList());
        if (userProperCarnets.size() == 0) {
            return false;

        }
        Carnet carnet = userProperCarnets.get(userProperCarnets.size() - 1);
        // w zależności od wymagań biznesowych : tutaj zwracamy wejście na najdłużej ważny karnet,
        // który ma jeszcze wejścia, warto pomyśleć nad zmianą tej logiki

        carnet.setEntrances(carnet.getEntrances() + 1);
        carnetService.update(carnet);
        return true;
    }

    public String encodeUserPassword(String password) {
        return passwordEncoder.encode(password);
    }






}


