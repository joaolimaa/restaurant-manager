package fiap.restaurant_manager.application.usecase;

import fiap.restaurant_manager.application.gateway.UserGateway;
import fiap.restaurant_manager.domain.entity.UserEntity;
import fiap.restaurant_manager.domain.exception.UserNotFoundException;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.restaurant_manager.infrastructure.util.formatters.formatCPF;

@Service
public class UserUseCase {
    private final UserGateway userGateway;

    public UserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public List<UserEntity> findAllUsers() {
        return userGateway.findAllUsers();
    }

    public UserEntity createUser(UserEntity user) {
        user.setCpf(formatCPF(user.getCpf()));
        return userGateway.save(user);
    }

    public UserEntity updateUser(Long id, UserEntity userRequest) {
        findUserById(id);
        return userGateway.update(id, userRequest);
    }

    public boolean deleteUser(Long id) {
        findUserById(id);
        return userGateway.deleteById(id);
    }

    public UserEntity findUserById(Long id) {
        val existingUser = userGateway.findById(id);
        if (existingUser == null) {
            throw new UserNotFoundException();
        }
        return existingUser;
    }
}
