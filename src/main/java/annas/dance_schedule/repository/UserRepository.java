package annas.dance_schedule.repository;

import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByRole(String role);

    @Modifying
    @Transactional
    @Query("update User u set u.classesParticipating = ?1 WHERE u.id = ?2")
    void updateUserLessons(List<Lesson> classesParticipating, Long id);

    @Modifying
    @Transactional
    @Query("update User u SET u.email=?1, u.role=?2, u.enabled=?3," +
            " u.firstName=?4, u.lastName=?5, u.password=?6 WHERE u.id = ?7")
    void updateUser(String email, String role, boolean enabled, String firstName, String lastName, String password, Long id);
}
