package fiap.restaurant_manager.adapters.persistence.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OperatingHours")
public class OperatingHoursEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operating_hours")
    private long idOperatingHours;

    private DayOfWeek dayOfWeek;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    public OperatingHoursEntity(DayOfWeek dayOfWeek, ZonedDateTime startTime, ZonedDateTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
