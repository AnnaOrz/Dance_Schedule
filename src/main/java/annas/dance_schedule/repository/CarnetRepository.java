package annas.dance_schedule.repository;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CarnetRepository extends JpaRepository <Carnet, Long> {
    Optional<Carnet> findAllByExpireDateBefore(LocalDate activeTo);
    Optional<Carnet> findAllByPrice(BigDecimal price);
    List<Carnet> findAllByUserId(Long userId);
    @Modifying
    @Transactional
    @Query("update Carnet c set c.accessNumber=?1, c.entrances=?2, c.expireDate=?3, " +
            "c.price=?4, c.startDate=?5, c.user=?6 WHERE c.id = ?7")
    void update(Integer accessNumber, Integer entrances, LocalDate expireDate,
                BigDecimal price, LocalDate startDate, User user, Long id);

    List<Carnet> findAllByUserAndExpireDateAfter(User user, LocalDate date);
    List<Carnet> findAllByUserAndExpireDateBefore(User user, LocalDate date);
}
