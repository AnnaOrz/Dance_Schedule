package annas.dance_schedule.repository;

import annas.dance_schedule.model.CarnetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarnetTypeRepository extends JpaRepository<CarnetType, Long> {
    @Query("SELECT c FROM CarnetType c WHERE c.isAvailable = true")
    List<CarnetType> findAllAvailable();
    //assume there is no option all carnetTypes being unavailable
}
