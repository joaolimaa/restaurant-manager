package fiap.restaurant_manager.adapters.persistence.repository;

import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {}
