package annas.dance_schedule.repository;


import annas.dance_schedule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long> {

}
