package fiap.restaurant_manager.application.usecase;

import fiap.restaurant_manager.application.gateway.RestaurantGateway;
import fiap.restaurant_manager.domain.entity.restaurant.RestaurantEntity;
import fiap.restaurant_manager.domain.exception.InvalidAddressException;
import fiap.restaurant_manager.domain.exception.RestaurantNotFoundException;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.restaurant_manager.infrastructure.util.formatters.formatPostalCode;

@Service
public class RestaurantUseCase {
    private final RestaurantGateway restaurantGateway;
    private final ViaCepUseCase viaCepUseCase;

    public RestaurantUseCase(RestaurantGateway restaurantGateway,
                             ViaCepUseCase viaCepUseCase) {
        this.restaurantGateway = restaurantGateway;
        this.viaCepUseCase = viaCepUseCase;
    }

    public List<RestaurantEntity> findAllRestaurants() {
        return restaurantGateway.findAllRestaurants();
    }

    public RestaurantEntity createRestaurant(RestaurantEntity restaurant) {
        val address = restaurant.getAddress();
        val validationResponse = viaCepUseCase.validateAddress(formatPostalCode(address.getPostalCode()));
        if (validationResponse == null) {
            throw new InvalidAddressException();
        }

        // Atualiza o endereço com os dados retornados pela API ViaCEP
        address.setStreet(validationResponse.getLogradouro());
        address.setNeighborhood(validationResponse.getBairro());
        address.setCity(validationResponse.getLocalidade());
        address.setState(validationResponse.getUf());

        return restaurantGateway.save(restaurant);
    }

    public RestaurantEntity updateRestaurant(Long id, RestaurantEntity restaurantRequest) {
        findRestaurantById(id);
        return restaurantGateway.update(id, restaurantRequest);
    }

    public boolean deleteRestaurant(Long id) {
        findRestaurantById(id);
        return restaurantGateway.deleteById(id);
    }

    public RestaurantEntity findRestaurantById(Long id) {
        val existingRestaurant = restaurantGateway.findById(id);
        if (existingRestaurant == null) {
            throw new RestaurantNotFoundException();
        }
        return existingRestaurant;
    }
}
