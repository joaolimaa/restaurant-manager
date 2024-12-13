package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.UserDTO;
import fiap.restaurant_manager.adapters.persistence.entities.UserEntity;
import fiap.restaurant_manager.domain.entities.User;

public class UserControllerMapper {

    public User toUserDomain(UserDTO userDTO) {
        return new User(
                userDTO.name(),
                userDTO.email(),
                userDTO.cpf());
    }

    public UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getName(),
                user.getEmail(),
                user.getCpf());
    }

    public UserDTO toUserDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getCpf()
        );
    }
}
