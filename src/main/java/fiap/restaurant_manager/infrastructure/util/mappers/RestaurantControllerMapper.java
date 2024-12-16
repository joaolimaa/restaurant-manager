package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.domain.entities.Restaurant;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantControllerMapper {
    private final OperatingHoursMapper mapperOperatingHour;
    private final AddressMapper mapperAdress;

    public Restaurant toRestaurantDomain(RestaurantDTO restaurant) {

        return new Restaurant(
                restaurant.name(),
                mapperAdress.toAddressDomain(restaurant.address()),
                restaurant.kitchenType(),
                restaurant.cnpj(),
                restaurant.operatingHoursDTO().stream().map(mapperOperatingHour::toOperatingHoursDomain).toList(),
                restaurant.capacity());
    }

    public RestaurantEntity toRestaurantEntity(Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getName(),
                mapperAdress.toAddressEntity(restaurant.getAddress()),
                restaurant.getKitchenType(),
                restaurant.getCnpj(),
                restaurant.getOperatingHours().stream().map(mapperOperatingHour::toOperatingHoursEntity).toList(),
                restaurant.getCapacity()
        );
    }

    public RestaurantDTO toRestaurantDTO(RestaurantEntity restaurant) {
        return new RestaurantDTO(
                restaurant.getName(),
                mapperAdress.toAddressDTO(restaurant.getAddress()),
                restaurant.getKitchenType(),
                restaurant.getCnpj(),
                restaurant.getOperatingHours().stream().map(mapperOperatingHour::toOperatingHoursDTO).toList(),
                restaurant.getCapacity()
        );
    }
}
