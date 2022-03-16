package annas.dance_schedule.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
public class CarnetType {
    // CarnetType is offer in studio. In spite of Carnet it has no start/expiry date and no User

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private Integer entrances;
    @NotNull private BigDecimal price;
    @NotNull private Integer accessNumber;
    /* cheaper classes - 1 , extended classes - 2 ,
    accessNumber in carnet must be the same as accesN in class or user can't reserve a place. */

    @NotNull private String description;
    @NotNull boolean isAvailable;
    //true will be available to buy by users

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
