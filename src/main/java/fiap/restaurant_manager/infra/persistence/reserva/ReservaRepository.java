package fiap.restaurant_manager.infra.persistence.reserva;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepository extends MongoRepository<ReservaEntity, Long> {
}
