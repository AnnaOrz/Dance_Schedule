package annas.dance_schedule.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CarnetType {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        private Integer entrances;
        private BigDecimal price;

        @NotNull
        private Integer accessNumber;

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
}
