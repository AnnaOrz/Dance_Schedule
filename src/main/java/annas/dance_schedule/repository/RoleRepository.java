package annas.dance_schedule.repository;

import annas.dance_schedule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    Optional<Role> findById(Long aLong);
    Optional<Role> findByName(String name);
}
