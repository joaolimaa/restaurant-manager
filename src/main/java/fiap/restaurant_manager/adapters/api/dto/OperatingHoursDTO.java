package fiap.restaurant_manager.adapters.api.dto;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;


public record OperatingHoursDTO(DayOfWeek dayOfWeek,
                                ZonedDateTime startTime,
                                ZonedDateTime endTime) {}
