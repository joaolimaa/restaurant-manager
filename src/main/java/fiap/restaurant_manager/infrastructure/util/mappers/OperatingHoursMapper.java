package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.OperatingHoursDTO;
import fiap.restaurant_manager.adapters.persistence.entities.OperatingHoursEntity;
import fiap.restaurant_manager.domain.entities.OperatingHours;

import java.util.ArrayList;
import java.util.List;

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

    public OperatingHours toOperatingHours(OperatingHoursEntity operatingHours) {
        return new OperatingHours(
                operatingHours.getDayOfWeek(),
                operatingHours.getStartTime(),
                operatingHours.getEndTime());
    }

    public List<OperatingHoursEntity> toOperatingHoursEntityList(List<OperatingHours> operatingHoursList) {
        final List<OperatingHoursEntity> mapped = new ArrayList<>();

        operatingHoursList.forEach((operatingHours)-> mapped.add(toOperatingHoursEntity(operatingHours)));

        return mapped;
    }

    public List<OperatingHours> toOperatingHoursDomainList(List<OperatingHoursEntity> savedOperatingHours) {
        final List<OperatingHours> mapped = new ArrayList<>();

        savedOperatingHours.forEach((operatingHours)-> mapped.add(toOperatingHours(operatingHours)));

        return mapped;
    }
}
