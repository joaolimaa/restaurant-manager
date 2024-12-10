package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.domain.entities.Restaurant;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RestaurantUseCase {
    private final RestaurantGateway restaurantGateway;

    public List<Restaurant> findAllRestaurants() {
        return restaurantGateway.findAllRestaurants();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {

        return restaurantGateway.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurantRequest) {
        return restaurantGateway.update(id, restaurantRequest);
    }

    public boolean deleteRestaurant(Long id) {
        return restaurantGateway.deleteById(id);
    }

    public Restaurant findRestaurantById(Long id) {
       return  restaurantGateway.findById(id);
    }
}
