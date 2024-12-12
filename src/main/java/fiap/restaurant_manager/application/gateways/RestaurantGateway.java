package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.adapters.persistence.repository.RestaurantRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class RestaurantGateway {
    private final RestaurantRepository restaurantRepository;

    public Collection<RestaurantEntity> findAll() {
        return restaurantRepository.findAll();
    }

    public Optional<RestaurantEntity> findById(Long id){
        return restaurantRepository.findById(id);
    }

    public void save(RestaurantEntity restaurantEntity) {
        restaurantRepository.save(restaurantEntity);
    }

    public void deleteById(Long id) {
        restaurantRepository.deleteById(id);
    }
}
