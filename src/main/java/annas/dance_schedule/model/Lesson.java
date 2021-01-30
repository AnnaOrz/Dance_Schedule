package annas.dance_schedule.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;


@Entity
@Table(name="classes")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    /*@Range(min= 0 , max = 20)*/
    @NotNull
    private Integer slots;

    private String place;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime beginTime;
    @NotNull
    private String state;
    //state could be: active, cancelled, starting
    @NotNull
    private String level;
    //Basic, Intermediate, Advanced, Open
    @NotNull
    private Integer accessNumber;


    @ManyToMany (mappedBy = "classesParticipating")
    private List<User> participants;

    @ManyToOne
    private User trainer;
    //zakładam że można utworzyć zajęcia bez trenera i wybrać go później.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime  getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime  beginTime) {
        this.beginTime = beginTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public Integer getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(Integer accessNumber) {
        this.accessNumber = accessNumber;
    }
}
