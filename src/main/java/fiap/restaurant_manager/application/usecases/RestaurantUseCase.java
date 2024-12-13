package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;

import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.infrastructure.util.mappers.RestaurantControllerMapper;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Collection;


@AllArgsConstructor
public class RestaurantUseCase {
    private final RestaurantGateway restaurantGateway;
    private final RestaurantControllerMapper mapper;

    public Collection<RestaurantDTO> findAllRestaurants() {
        return restaurantGateway.findAll().stream().map(mapper::toRestaurantDTO).toList();
    }

    public RestaurantDTO findRestaurantById(Long id) {
        return mapper.toRestaurantDTO(restaurantGateway.findById(id));
    }


    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        val restaurantDomain = mapper.toRestaurantDomain(restaurantDTO);
        val restaurantEntity = mapper.toRestaurantEntity(restaurantDomain);

        //Recuperar o ID e salvar o Adress
        //Recuperar o ID e salvar o OperationgHours

        return  mapper.toRestaurantDTO(restaurantGateway.save(restaurantEntity));
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
