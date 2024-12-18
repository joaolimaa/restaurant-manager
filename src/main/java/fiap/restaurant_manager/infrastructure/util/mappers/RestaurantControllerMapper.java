package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.domain.entities.Restaurant;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantControllerMapper {
    public Restaurant toRestaurantDomain(RestaurantDTO restaurant) {
        return new Restaurant(
                restaurant.name(),
                restaurant.postalCode(),
                restaurant.street(),
                restaurant.number(),
                restaurant.kitchenType(),
                restaurant.cnpj(),
                restaurant.capacity(),
                restaurant.initialTime(),
                restaurant.finalTime());
    }

    public RestaurantEntity toRestaurantEntity(Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getName(),
                restaurant.getPostalCode(),
                restaurant.getStreet(),
                restaurant.getNumber(),
                restaurant.getKitchenType(),
                restaurant.getCnpj(),
                restaurant.getCapacity(),
                restaurant.getInitialTime(),
                restaurant.getFinalTime()
        );
    }

    public RestaurantDTO toRestaurantDTO(RestaurantEntity restaurant) {
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getPostalCode(),
                restaurant.getStreet(),
                restaurant.getNumber(),
                restaurant.getKitchenType(),
                restaurant.getCnpj(),
                restaurant.getCapacity(),
                restaurant.getInitialTime(),
                restaurant.getFinalTime()
        );
    }
}
