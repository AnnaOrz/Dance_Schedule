package annas.dance_schedule.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private boolean enabled;

    @NotBlank
    @Email
    private String email;

    @ManyToMany
    private Set< Role > roles;

    @NotBlank
    private String username;

    private String password;
    @Transient //żeby nie było można zrobić persist na tym
    private String passwordConfirm;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Carnet> carnets;

    @ManyToMany
    private List<Lesson> classesParticipating;

    public List<Lesson> getClassesParticipating() {
        return classesParticipating;
    }

    public void setClassesParticipating(List<Lesson> classesParticipating) {
        this.classesParticipating = classesParticipating;
    }

    public List<Carnet> getCarnets() {
        return carnets;
    }

    public void setCarnets(List<Carnet> carnets) {
        this.carnets = carnets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
