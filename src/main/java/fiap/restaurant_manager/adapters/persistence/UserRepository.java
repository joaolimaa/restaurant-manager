package fiap.restaurant_manager.adapters.persistence;

import fiap.restaurant_manager.application.gateway.UserGateway;
import fiap.restaurant_manager.domain.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String>, UserGateway {}
