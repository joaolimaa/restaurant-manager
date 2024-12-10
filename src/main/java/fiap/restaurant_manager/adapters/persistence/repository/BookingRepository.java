package fiap.restaurant_manager.adapters.persistence.repository;

import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {}
