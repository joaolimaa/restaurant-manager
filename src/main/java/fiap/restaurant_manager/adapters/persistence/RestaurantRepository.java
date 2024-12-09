package fiap.restaurant_manager.adapters.persistence;

import fiap.restaurant_manager.application.gateway.RestaurantGateway;
import fiap.restaurant_manager.domain.entity.restaurant.RestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<RestaurantEntity, String>, RestaurantGateway {}
