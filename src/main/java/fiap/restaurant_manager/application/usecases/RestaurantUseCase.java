package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class RestaurantUseCase {
    private final RestaurantGateway restaurantGateway;

    public Collection<RestaurantEntity> findAllRestaurants() {
        return restaurantGateway.findAll();
    }

    public void createRestaurant(RestaurantEntity restaurantEntity) {
        restaurantGateway.save(restaurantEntity);
    }

    public void updateRestaurant(Long id, RestaurantEntity restaurantEntity) {
        findRestaurantById(id);
        restaurantGateway.save(restaurantEntity);
    }

    public void deleteRestaurant(Long id) {
        restaurantGateway.deleteById(id);
    }

    public Optional<RestaurantEntity> findRestaurantById(Long id) {
        return  restaurantGateway.findById(id);
    }
}
