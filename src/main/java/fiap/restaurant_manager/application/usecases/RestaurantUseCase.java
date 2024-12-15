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

        // It was necessary to split the flow to work with their own gateways so the data could persist without any
        // errors during the POST request. The error I am mentioning is: "persistent instance references an unsaved
        // transient instance." I learned about this error during cucumber tests.
        persistAddressEntity(restaurantEntity);
        persistOperatingHoursEntity(restaurantEntity);

        // Save RestaurantEntity after saving its dependencies
        var savedRestaurant = restaurantGateway.save(restaurantEntity);

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

    // This one is throwing the following exception when called:
    //
    // org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientObjectException:
    // persistent instance references an unsaved transient instance of
    // 'fiap.restaurant_manager.adapters.persistence.entities.AddressEntity'
    // (save the transient instance before flushing)\n\tat org.springframework.orm.jpa.EntityManagerFactoryUtils.
    // convertJpaAccessExceptionIfPossible(EntityManagerFactoryUtils.java:368)
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


    // This was a try to fix the transient error at update flow. Here is 3:16 am and, I am exhausted.
    // I can try to fix this one tomorrow, but I will leave this one for last.
    @Transactional
    public RestaurantDTO updateRestaurantV2(Long id, RestaurantDTO restaurantDTO) {
        // Fetch existing restaurant domain from the database
        val existingRestaurantDomain = restaurantControllerMapper.toRestaurantDomain(findRestaurantById(id));

        // Map updated details to a domain object
        val updatedRestaurantDomain = restaurantControllerMapper.toRestaurantDomain(restaurantDTO);

        // Update primitive fields
        existingRestaurantDomain.setName(updatedRestaurantDomain.getName());
        existingRestaurantDomain.setKitchenType(updatedRestaurantDomain.getKitchenType());
        existingRestaurantDomain.setCnpj(updatedRestaurantDomain.getCnpj());
        existingRestaurantDomain.setCapacity(updatedRestaurantDomain.getCapacity());

        // Handle AddressEntity
        if (updatedRestaurantDomain.getAddress() != null) {
            // Persist the updated address entity
            val updatedAddressEntity = addressGateway.save(addressMapper.toAddressEntity(updatedRestaurantDomain.getAddress()));
            // Map the saved entity back to AddressDomain and set it
            existingRestaurantDomain.setAddress(addressMapper.toAddressDomain(updatedAddressEntity));
        }

        // Handle OperatingHoursEntity
        if (updatedRestaurantDomain.getOperatingHours() != null) {
            // Persist each updated operating hours entity
            val updatedOperatingHoursEntities = operatingHoursGateway.saveAll(
                    operatingHoursMapper.toOperatingHoursEntityList(updatedRestaurantDomain.getOperatingHours())
            );
            // Map the persisted entities back to the domain list
            existingRestaurantDomain.setOperatingHours(operatingHoursMapper.toOperatingHoursDomainList(updatedOperatingHoursEntities));
        }

        // Save the updated restaurant entity
        val updatedRestaurantEntity = restaurantGateway.save(restaurantControllerMapper.toRestaurantEntity(existingRestaurantDomain));

        // Return the updated restaurant as a DTO
        return restaurantControllerMapper.toRestaurantDTO(updatedRestaurantEntity);
    }



    public void deleteRestaurant(Long id) {
        restaurantGateway.deleteById(id);
    }

}
