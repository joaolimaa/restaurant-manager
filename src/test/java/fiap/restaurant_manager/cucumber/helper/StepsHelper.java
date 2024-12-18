package fiap.restaurant_manager.cucumber.helper;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.domain.enums.KitchenType;
import lombok.experimental.UtilityClass;

import java.time.*;
import java.util.List;

@UtilityClass
public class StepsHelper {

    public static RestaurantDTO getRestaurantDto(List<String> details) {
        return new RestaurantDTO(
                details.get(0),                   // name
                details.get(1),                   // postalCode
                details.get(2),                   // street
                details.get(3),                   // number
                parseKitchenType(details.get(4)), // kitchenType
                details.get(5),                   // cnpj
                Integer.parseInt(details.get(6)), // capacity
                LocalTime.parse(details.get(7)),  // initialTime
                LocalTime.parse(details.get(8))   // finalTime
        );
    }

    public static  KitchenType parseKitchenType(String kitchenType) {
        return KitchenType.valueOf(kitchenType.toUpperCase().replaceAll("\"", ""));
    }
}
