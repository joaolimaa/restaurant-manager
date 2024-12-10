package fiap.restaurant_manager.adapters.api.mapper;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.domain.entities.Restaurant;


public class RestaurantControllerMapper {

    public Restaurant toRestaurant(RestaurantDTO restaurant) {
        return new Restaurant(
                restaurant.name(),
                restaurant.addressEntity(),
                restaurant.kitchenType(),
                restaurant.cnpj(),
                restaurant.operatingHourEntities(),
                restaurant.capacity());
    }

    public RestaurantDTO toDTO(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getName(),
                restaurant.getAddressEntity(),
                restaurant.getKitchenType(),
                restaurant.getCnpj(),
                restaurant.getOperatingHourEntities(),
                restaurant.getCapacity()
        );
    }
}
