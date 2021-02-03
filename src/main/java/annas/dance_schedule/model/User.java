package annas.dance_schedule.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private boolean enabled;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    private String role;

    private String password;

    @Transient
    private String passwordConfirm;


    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user")
    private List<Carnet> carnets;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Lesson> classesParticipating;

    private String firstName;
    private String lastName;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addCarnet(Carnet carnet) {
        carnets.add(carnet);
    }
}
