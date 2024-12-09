package fiap.restaurant_manager.application.gateway;

import fiap.restaurant_manager.domain.entity.restaurant.RestaurantEntity;

import java.util.List;

public interface RestaurantGateway {

    List<RestaurantEntity> findAllRestaurants();
    RestaurantEntity findById(Long id);
    RestaurantEntity save(RestaurantEntity restaurant);
    RestaurantEntity update(Long id, RestaurantEntity restaurant);
    boolean deleteById(Long id);
}
