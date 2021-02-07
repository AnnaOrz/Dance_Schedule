package annas.dance_schedule.repository;

import annas.dance_schedule.model.CarnetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface CarnetTypeRepository extends JpaRepository<CarnetType, Long> {
    @Query("SELECT c FROM CarnetType c WHERE c.isAvailable = true")
    List<CarnetType> findAllAvailable();

    @Modifying
    @Transactional
    @Query("UPDATE CarnetType c SET c.accessNumber=?1, c.entrances=?2, c.description=?3, " +
            "c.price=?4, c.isAvailable=?5 WHERE c.id = ?6")
    void update(Integer accessNumber, Integer entrances, String description,
                BigDecimal price, boolean isAvailable, Long id);
}
