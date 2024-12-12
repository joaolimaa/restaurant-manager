package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.domain.entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public class RestaurantControllerMapper {
    private final OperatingHoursMapper mapper;

    public Restaurant toRestaurantDomain(RestaurantDTO restaurant) {


        return new Restaurant(
                restaurant.name(),
                restaurant.address(),
                restaurant.kitchenType(),
                restaurant.cnpj(),
                mapper.toOperatingHoursDomain(restaurant),
                restaurant.capacity());
    }

    public RestaurantEntity toRestaurantEntity(Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getName(),
                restaurant.getAddressEntity(),
                restaurant.getKitchenType(),
                restaurant.getCnpj(),
                restaurant.getOperatingHourEntities(),
                restaurant.getCapacity()
        );
    }

    public RestaurantDTO toRestaurantDTO(RestaurantEntity restaurant) {
        return new RestaurantDTO(
                restaurant.getName(),
                restaurant.getAddressEntity(),
                restaurant.getKitchenType(),
                restaurant.getCnpj(),
                restaurant.getOperatingHours(),
                restaurant.getCapacity()
        );
    }
}
