package fiap.restaurant_manager.adapters.persistence.repository;

import fiap.restaurant_manager.adapters.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
