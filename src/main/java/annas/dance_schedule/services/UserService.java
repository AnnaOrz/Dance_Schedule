package annas.dance_schedule.services;

import annas.dance_schedule.exceptions.UserAlreadyExistException;
import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import annas.dance_schedule.model.UserDto;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerNewUserAccount(UserDto userDto)
            throws UserAlreadyExistException {

        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            + userDto.getEmail());
        } else {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEnabled(false);
            user.setRole("USER");
            return user;
        }
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void update(User user) {
        Optional<User> oldUser = userRepository.findById(user.getId());
        if (oldUser.isPresent()) {
            userRepository.updateUser(
                    user.getEmail(),
                    user.getRole(),
                    user.isEnabled(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    user.getId());
        } else userRepository.save(user);
    }
    public void activateUser(User user){
        user.setRole("USER");
        user.setEnabled(true);
        update(user);
    }
    @Transactional
    public boolean addOneEntranceToUserProperCarnet(User user, Lesson lesson){
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
        carnet.setEntrances(carnet.getEntrances() + 1);
        return true;
    }
    public String EncodeUserPassword(String password){
        return passwordEncoder.encode(password);
    }

}


