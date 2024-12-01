package fiap.restaurant_manager.infra.repository;


import fiap.restaurant_manager.infra.database.ReservaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepository extends MongoRepository<ReservaEntity, Long> {
}
