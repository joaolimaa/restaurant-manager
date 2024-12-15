package fiap.restaurant_manager.cucumber.helper;

import fiap.restaurant_manager.adapters.api.dto.AddressDTO;
import fiap.restaurant_manager.adapters.api.dto.OperatingHoursDTO;
import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.domain.enums.KitchenType;
import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@UtilityClass
public class StepsHelper {


    public static RestaurantDTO getRestaurantDto(
        List<String> details,
        AddressDTO address,
        KitchenType kitchenType,
        OperatingHoursDTO operatingHour
    ){
        return new RestaurantDTO (
                details.get(0),
                address,
                kitchenType,
                details.get(8),
                List.of(operatingHour),
                Integer.parseInt(details.get(9))
        );
    }

    public static AddressDTO buildAddress(List<String> details) {
        return new AddressDTO(
                details.get(1),  // postalCode
                details.get(2),  // city
                details.get(3),  // state
                details.get(4),  // neighborhood
                details.get(5),  // street
                details.get(6)   // number
        );
    }

    public static  KitchenType parseKitchenType(String kitchenType) {
        return KitchenType.valueOf(kitchenType.toUpperCase().replaceAll("\"", ""));
    }

    public static  OperatingHoursDTO buildOperatingHours(String operatingHours) {
        // Example format: "MONDAY, HH:mm-HH:mm"
        String[] operatingHoursParts = operatingHours.split(", ");
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(operatingHoursParts[0].toUpperCase());
        String[] timeRange = operatingHoursParts[1].split("-");

        LocalTime startTime = LocalTime.parse(timeRange[0], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime endTime = LocalTime.parse(timeRange[1], DateTimeFormatter.ofPattern("HH:mm"));

        return new OperatingHoursDTO(
                dayOfWeek,
                ZonedDateTime.of(LocalDate.now(), startTime, ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDate.now(), endTime, ZoneId.systemDefault())
        );
    }
}
