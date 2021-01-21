package annas.dance_schedule.repository;

import annas.dance_schedule.model.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface CarnetRepository extends JpaRepository <Carnet, Long> {
    Optional<Carnet> findAllByExpireDateBefore(Date activeTo);
    Optional<Carnet> findAllByPrice(BigDecimal price);
}
