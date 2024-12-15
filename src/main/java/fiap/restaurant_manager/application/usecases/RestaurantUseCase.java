package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.application.gateways.AddressGateway;
import fiap.restaurant_manager.application.gateways.OperatingHoursGateway;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.infrastructure.util.mappers.RestaurantControllerMapper;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@AllArgsConstructor
public class RestaurantUseCase {
    private final RestaurantGateway restaurantGateway;
    private final RestaurantControllerMapper mapper;
    private final OperatingHoursGateway operatingHoursGateway;
    private final AddressGateway addressGateway;


    public Collection<RestaurantDTO> findAllRestaurants() {
        return restaurantGateway.findAll().stream().map(mapper::toRestaurantDTO).toList();
    }

    public RestaurantDTO findRestaurantById(Long id) {
        return mapper.toRestaurantDTO(restaurantGateway.findById(id));
    }


    @Transactional
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {

        val restaurantDomain = mapper.toRestaurantDomain(restaurantDTO);
        val restaurantEntity = mapper.toRestaurantEntity(restaurantDomain);

        // It was necessary to split the flow to work with their own gateways so the data could persist without any
        // errors during the POST request. The error I am mentioning is: "persistent instance references an unsaved
        // transient instance." I learned about this error during cucumber tests.
        persistAddressEntity(restaurantEntity);
        persistOperatingHoursEntity(restaurantEntity);

        // Save RestaurantEntity after saving its dependencies
        var savedRestaurant = restaurantGateway.save(restaurantEntity);

        return mapper.toRestaurantDTO(savedRestaurant);
    }

    private void persistOperatingHoursEntity(RestaurantEntity restaurantEntity) {
        if (restaurantEntity.getOperatingHours() != null) {
            restaurantEntity.setOperatingHours(operatingHoursGateway.saveAll(restaurantEntity.getOperatingHours()));
        }
    }

    private void persistAddressEntity(RestaurantEntity restaurantEntity) {
        if (restaurantEntity.getAddress() != null) {
            restaurantEntity.setAddress(addressGateway.save(restaurantEntity.getAddress()));
        }
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {

        val restaurantDomain = mapper.toRestaurantDomain(findRestaurantById(id));
        val restaurantDomainNew = mapper.toRestaurantDomain(restaurantDTO);

        restaurantDomain.setName(restaurantDomainNew.getName());
        restaurantDomain.setAddress(restaurantDomainNew.getAddress());
        restaurantDomain.setKitchenType(restaurantDomainNew.getKitchenType());
        restaurantDomain.setCnpj(restaurantDomainNew.getCnpj());
        restaurantDomain.setOperatingHours(restaurantDomainNew.getOperatingHours());
        restaurantDomain.setCapacity(restaurantDomainNew.getCapacity());

       return mapper.toRestaurantDTO(restaurantGateway.save(mapper.toRestaurantEntity(restaurantDomain)));
    }

    public void deleteRestaurant(Long id) {
        restaurantGateway.deleteById(id);
    }

}
