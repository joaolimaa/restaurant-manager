package fiap.restaurant_manager.adapters.api.mapper;

import fiap.restaurant_manager.adapters.api.dto.UserDTO;
import fiap.restaurant_manager.domain.entities.User;

public class UserControllerMapper {

    public User toUser(UserDTO userDTO) {
        return new User(
                userDTO.name(),
                userDTO.email(),
                userDTO.cpf());
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getNome(),
                user.getEmail(),
                user.getCpf()
        );
    }
}
