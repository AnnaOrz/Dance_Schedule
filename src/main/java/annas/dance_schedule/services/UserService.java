package annas.dance_schedule.services;

import annas.dance_schedule.exceptions.UserAlreadyExistException;
import annas.dance_schedule.model.User;
import annas.dance_schedule.model.UserDto;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            user.setPassword(userDto.getPassword());
            user.setEnabled(false);
            return user;
        }
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}


