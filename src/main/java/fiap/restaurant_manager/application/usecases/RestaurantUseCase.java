package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.application.gateways.AddressGateway;
import fiap.restaurant_manager.application.gateways.OperatingHoursGateway;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.infrastructure.util.mappers.OperatingHoursMapper;
import fiap.restaurant_manager.infrastructure.util.mappers.RestaurantControllerMapper;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.transaction.annotation.Transactional;
import fiap.restaurant_manager.infrastructure.util.mappers.AddressMapper;

import java.util.Collection;

@AllArgsConstructor
public class RestaurantUseCase {
    private final RestaurantGateway restaurantGateway;
    private final RestaurantControllerMapper restaurantControllerMapper;
    private final AddressMapper addressMapper;
    private final OperatingHoursMapper operatingHoursMapper;
    private final OperatingHoursGateway operatingHoursGateway;
    private final AddressGateway addressGateway;

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
        persistAddressEntity(restaurantEntity);
        persistOperatingHoursEntity(restaurantEntity);
        val savedRestaurant = restaurantGateway.save(restaurantEntity);
        return restaurantControllerMapper.toRestaurantDTO(savedRestaurant);
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
        val restaurantDomain = restaurantControllerMapper.toRestaurantDomain(findRestaurantById(id));
        val restaurantDomainNew = restaurantControllerMapper.toRestaurantDomain(restaurantDTO);
        restaurantDomain.setName(restaurantDomainNew.getName());
        restaurantDomain.setAddress(restaurantDomainNew.getAddress());
        restaurantDomain.setKitchenType(restaurantDomainNew.getKitchenType());
        restaurantDomain.setCnpj(restaurantDomainNew.getCnpj());
        restaurantDomain.setOperatingHours(restaurantDomainNew.getOperatingHours());
        restaurantDomain.setCapacity(restaurantDomainNew.getCapacity());
        return restaurantControllerMapper.toRestaurantDTO(restaurantGateway.save(restaurantControllerMapper.toRestaurantEntity(restaurantDomain)));
    }

    @Transactional
    public RestaurantDTO updateRestaurantV2(Long id, RestaurantDTO restaurantDTO) {
        val existingRestaurantDomain = restaurantControllerMapper.toRestaurantDomain(findRestaurantById(id));
        val updatedRestaurantDomain = restaurantControllerMapper.toRestaurantDomain(restaurantDTO);

        existingRestaurantDomain.setName(updatedRestaurantDomain.getName());
        existingRestaurantDomain.setKitchenType(updatedRestaurantDomain.getKitchenType());
        existingRestaurantDomain.setCnpj(updatedRestaurantDomain.getCnpj());
        existingRestaurantDomain.setCapacity(updatedRestaurantDomain.getCapacity());

        if (updatedRestaurantDomain.getAddress() != null) {
            val updatedAddressEntity = addressGateway.save(addressMapper.toAddressEntity(updatedRestaurantDomain.getAddress()));
            existingRestaurantDomain.setAddress(addressMapper.toAddressDomain(updatedAddressEntity));
        }

        if (updatedRestaurantDomain.getOperatingHours() != null) {
            val updatedOperatingHoursEntities = operatingHoursGateway.saveAll(
                    operatingHoursMapper.toOperatingHoursEntityList(updatedRestaurantDomain.getOperatingHours())
            );
            existingRestaurantDomain.setOperatingHours(operatingHoursMapper.toOperatingHoursDomainList(updatedOperatingHoursEntities));
        }

        val updatedRestaurantEntity = restaurantGateway.save(restaurantControllerMapper.toRestaurantEntity(existingRestaurantDomain));
        return restaurantControllerMapper.toRestaurantDTO(updatedRestaurantEntity);
    }

    public void deleteRestaurant(Long id) {
        restaurantGateway.deleteById(id);
    }
}
