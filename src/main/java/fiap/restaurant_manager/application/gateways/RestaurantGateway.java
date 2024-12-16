package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.adapters.persistence.repository.RestaurantRepository;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class RestaurantGateway {
    private final RestaurantRepository restaurantRepository;

    public Collection<RestaurantEntity> findAll() {
        return restaurantRepository.findAll();
    }

    public RestaurantEntity findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("O Restaurante " + id + " não encontrada."));
    }

    public RestaurantEntity save(RestaurantEntity restaurantEntity) {
        return restaurantRepository.save(restaurantEntity);
    }

    public void deleteById(Long id) {
        restaurantRepository.deleteById(id);
    }
}
