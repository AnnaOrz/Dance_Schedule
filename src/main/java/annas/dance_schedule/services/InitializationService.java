package annas.dance_schedule.services;

import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.Role;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.RoleRepository;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

@Component
public class InitializationService {

    private final UserService userservice;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final LessonService lessonService;

    public InitializationService(UserService userservice, RoleRepository roleRepository, UserRepository userRepository, LessonService lessonService) {
        this.userservice = userservice;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.lessonService = lessonService;
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
            user.setLastName("example");
            user.setPassword(userservice.encodeUserPassword("admin"));
            user.setEnabled(true);
            Collection<Role> userRoles = new LinkedList<>();
            userRoles.add(roleRepository.findByName("ROLE_ADMIN").get());
            user.setRoles(userRoles);
            userservice.saveUser(user);
        }

    }

    @PostConstruct
    private void addUser(){
        if(userRepository.findByEmail("user@user").isEmpty()){
            User user = new User();
            user.setId(2L);
            user.setEmail("user@user");
            user.setFirstName("user");
            user.setLastName("example");
            user.setPassword(userservice.encodeUserPassword("user"));
            user.setEnabled(true);
            Collection<Role> userRoles = new LinkedList<>();
            userRoles.add(roleRepository.findByName("ROLE_USER").get());
            user.setRoles(userRoles);
            userservice.saveUser(user);
        }
    }

    @PostConstruct
    private void addClassTomorrow(){
        Lesson lesson = new Lesson();
        lesson.setAccessNumber(1);
        lesson.setLevel("Medium");
        lesson.setBeginTime(LocalDateTime.now().plusDays(1));
        lesson.setName("TestingClass");
        lesson.setSlots(8);
        lesson.setState("active");
    }
}
