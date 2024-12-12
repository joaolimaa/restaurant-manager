package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.OperatingHoursDTO;
import fiap.restaurant_manager.adapters.persistence.entities.OperatingHoursEntity;
import fiap.restaurant_manager.domain.entities.OperatingHours;

public class OperatingHoursMapper {

    public OperatingHours toOperatingHoursDomain(OperatingHoursDTO operatingHours) {
        return new OperatingHours(
                operatingHours.dayOfWeek(),
                operatingHours.startTime(),
                operatingHours.endTime());
    }

    public OperatingHoursEntity toOperatingHoursEntity(OperatingHours operatingHours) {
        return new OperatingHoursEntity(
                operatingHours.getDayOfWeek(),
                operatingHours.getStartTime(),
                operatingHours.getEndTime());
    }

    public OperatingHoursDTO toOperatingHoursDTO(OperatingHoursEntity operatingHours) {
        return new OperatingHoursDTO(
                operatingHours.getDayOfWeek(),
                operatingHours.getStartTime(),
                operatingHours.getEndTime());
    }
}
