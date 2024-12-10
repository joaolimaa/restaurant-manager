package fiap.restaurant_manager.adapters.persistence.repository;


import fiap.restaurant_manager.adapters.persistence.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends JpaRepository<BookEntity, Long> {
}
