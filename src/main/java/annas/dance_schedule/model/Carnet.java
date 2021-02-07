package annas.dance_schedule.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class Carnet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer entrances;

    @NotNull
    private BigDecimal price;


    @NotNull(message = "Wybierz datę startu karnetu")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //bez tego nie działa z formularzem dobrze
    @FutureOrPresent(message = "nie możesz wybrać daty z przeszłości")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @FutureOrPresent
    private LocalDate expireDate;
    // przy utworzeniu karnetu automatycznie ustawiana na startDate + 30dni

    @NotNull
    private Integer accessNumber;
    // zajęcia z poziomu 1 najtańsze, zajęcia z poziomu 2 droższe. Mając 2 można wejść też na 1 ale nie odwrotnie

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull(message = "karnet musi by przypisany do użytkownika")
    private User user;
    //jeden użytkownik może mieć wiele karnetów ale każdy karnet ma tylko jednego użytkownika

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEntrances() {
        return entrances;
    }

    public void setEntrances(Integer entrances) {
        this.entrances = entrances;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Integer getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(Integer accessNumber) {
        this.accessNumber = accessNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

