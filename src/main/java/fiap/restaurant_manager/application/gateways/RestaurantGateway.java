package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.domain.entities.Restaurant;

import java.util.List;

public interface RestaurantGateway {

    List<Restaurant> findAllRestaurants();
    Restaurant findById(Long id);
    Restaurant save(Restaurant restaurant);
    Restaurant update(Long id, Restaurant restaurant);
    boolean deleteById(Long id);
}
