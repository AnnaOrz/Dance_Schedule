package annas.dance_schedule.repository;

import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.Role;
import annas.dance_schedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByRoles(Role role);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.classesParticipating = ?1 WHERE u.id = ?2")
    void updateUserLessons(List<Lesson> classesParticipating, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email=?1, u.enabled=?2," +
            " u.firstName=?3, u.lastName=?4, u.password=?5 WHERE u.id = ?6")
    void updateUser(String email, boolean enabled, String firstName, String lastName, String password, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.roles = ?1 WHERE u.id = ?2")
    void updateUserRoles(Collection<Role> roles, Long id);
}
