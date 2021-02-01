package annas.dance_schedule.repository;

import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findLessonsByBeginTimeBefore(LocalDateTime localDateTime);
    List<Lesson> findLessonsByBeginTimeAfter(LocalDateTime localDateTime);

    @Modifying
    @Transactional
    @Query("update Lesson c set c.name = ?1, c.beginTime = ?2, c.state= ?3, " +
            "c.slots=?4, c.accessNumber= ?5, c.level=?6, c.place=?7, c.trainer=?8, c.reserveList=?9 WHERE c.id = ?10")
    void update(String name, LocalDateTime beginTime, String state, Integer slots,
                Integer accessNumber, String level, String place, User trainer, List<User> reserveList, Long id);

    @Modifying
    @Transactional
    @Query("update Lesson c set c.participants=?1, c.reserveList=?2 WHERE c.id = ?3")
    void updateParticipants(List <User> participants, List<User> reserveList, Long id);


}
