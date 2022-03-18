package annas.dance_schedule.services;

import annas.dance_schedule.model.Role;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.RoleRepository;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;

@Component
public class InitializationService {

    private final UserService userservice;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public InitializationService(UserService userservice, RoleRepository roleRepository, UserRepository userRepository) {

        this.userservice = userservice;

        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @PostConstruct
    private void addRoles(){
        if(roleRepository.findAll().size() < 2) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            Role roleTrainer = new Role("ROLE_TRAINER");
            roleRepository.saveAndFlush(roleAdmin);
            roleRepository.saveAndFlush(roleUser);
            roleRepository.saveAndFlush(roleTrainer);
        }

    }

    @PostConstruct
    private void addAdmin() {
        if (userRepository.findByEmail("admin@admin").isEmpty()) {
            User user = new User();
            user.setId(1L);
            user.setEmail("admin@admin");
            user.setFirstName("admin");
            user.setLastName("adminos");
            user.setPassword(userservice.encodeUserPassword("admin"));
            user.setEnabled(false);
            Collection<Role> userRoles = new LinkedList<>();
            userRoles.add(roleRepository.findByName("ROLE_ADMIN").get());
            user.setRoles(userRoles);
            userservice.saveUser(user);
        }

    }
}
