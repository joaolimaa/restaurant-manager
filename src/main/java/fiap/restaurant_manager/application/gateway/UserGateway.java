package fiap.restaurant_manager.application.gateway;

import fiap.restaurant_manager.domain.entity.RestaurantEntity;
import fiap.restaurant_manager.domain.entity.UserEntity;

import java.util.List;

public interface UserGateway {

    List<UserEntity> findAllUsers();
    UserEntity findById(Long id);
    UserEntity save(UserEntity user);
    UserEntity update(Long id, UserEntity user);
    boolean deleteById(Long id);
}
