package fiap.restaurant_manager.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class OperatingHours {

    private DayOfWeek dayOfWeek;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;
}
