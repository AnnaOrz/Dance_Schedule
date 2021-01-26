package annas.dance_schedule.model;

import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CarnetDto {

    @NotNull(message = "Wybierz datę startu karnetu")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //bez tego nie działa z formularzem dobrze
    @FutureOrPresent(message = "nie możesz wybrać daty z przeszłości")
    private LocalDate startDate;

    @NotNull
    private Long carnetTypeId;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Long getCarnetTypeId() {
        return carnetTypeId;
    }

    public void setCarnetTypeId(Long carnetTypeId) {
        this.carnetTypeId = carnetTypeId;
    }
}
