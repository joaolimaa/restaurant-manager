package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.persistence.entities.UserEntity;
import fiap.restaurant_manager.application.gateways.UserGateway;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;

    public Collection<UserEntity> findAllUsers() {
        return userGateway.findAll();
    }

    public void createUser(UserEntity userEntity) {
        userGateway.save(userEntity);
    }

    public void updateUser(Long id, UserEntity userEntity) {
        findUserById(id);
        userGateway.save(userEntity);
    }

    public void deleteUser(Long id) {
        userGateway.deleteById(id);
    }

    public Optional<UserEntity> findUserById(Long id) {
        return  userGateway.findById(id);
    }
}
