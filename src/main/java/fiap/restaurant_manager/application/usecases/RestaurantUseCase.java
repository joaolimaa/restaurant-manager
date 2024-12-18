package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;

import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.infrastructure.util.mappers.RestaurantControllerMapper;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@AllArgsConstructor
public class RestaurantUseCase {
    private final RestaurantGateway restaurantGateway;
    private final RestaurantControllerMapper restaurantControllerMapper;

    public Collection<RestaurantDTO> findAllRestaurants() {
        return restaurantGateway.findAll().stream().map(restaurantControllerMapper::toRestaurantDTO).toList();
    }

    public RestaurantDTO findRestaurantById(Long id) {
        return restaurantControllerMapper.toRestaurantDTO(restaurantGateway.findById(id));
    }

    @Transactional
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        val restaurantDomain = restaurantControllerMapper.toRestaurantDomain(restaurantDTO);
        val restaurantEntity = restaurantControllerMapper.toRestaurantEntity(restaurantDomain);
        return restaurantControllerMapper.toRestaurantDTO(restaurantGateway.save(restaurantEntity));
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        val restaurantDomain = restaurantControllerMapper.toRestaurantDomain(findRestaurantById(id));
        val restaurantDomainNew = restaurantControllerMapper.toRestaurantDomain(restaurantDTO);
        restaurantDomain.setName(restaurantDomainNew.getName());
        restaurantDomain.setPostalCode(restaurantDomainNew.getPostalCode());
        restaurantDomain.setStreet(restaurantDomainNew.getStreet());
        restaurantDomain.setNumber(restaurantDomainNew.getNumber());
        restaurantDomain.setKitchenType(restaurantDomainNew.getKitchenType());
        restaurantDomain.setCapacity(restaurantDomainNew.getCapacity());
        restaurantDomain.setInitialTime(restaurantDomainNew.getInitialTime());
        restaurantDomain.setFinalTime(restaurantDomainNew.getFinalTime());
        return restaurantControllerMapper.toRestaurantDTO(restaurantGateway.save(restaurantControllerMapper.toRestaurantEntity(restaurantDomain)));
    }

    public void deleteRestaurant(Long id) {
        restaurantGateway.deleteById(id);
    }
}
