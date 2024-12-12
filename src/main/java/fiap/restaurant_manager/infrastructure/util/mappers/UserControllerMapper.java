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

    public UserEntity toUserEntity(UserDTO userDTO) {
        return new UserEntity(
                userDTO.name(),
                userDTO.email(),
                userDTO.cpf());
    }

    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getNome(),
                user.getEmail(),
                user.getCpf()
        );
    }
}
