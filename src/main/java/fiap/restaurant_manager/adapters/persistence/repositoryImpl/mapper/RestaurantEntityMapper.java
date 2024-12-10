package fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.domain.entities.Restaurant;


public class RestaurantEntityMapper {

    public RestaurantEntity toEntity(Restaurant restaurantDomain) {
        return new RestaurantEntity(
                restaurantDomain.getName(),
                restaurantDomain.getAddressEntity(),
                restaurantDomain.getKitchenType(),
                restaurantDomain.getCnpj(),
                restaurantDomain.getOperatingHourEntities(),
                restaurantDomain.getCapacity());
    }

    public Restaurant toDomain(RestaurantEntity reserva) {
        return new Restaurant(reserva.getName(),
                reserva.getAddressEntity(),
                reserva.getKitchenType(),
                reserva.getCnpj(),
                reserva.getOperatingHourEntities(),
                reserva.getCapacity());
    }
}
