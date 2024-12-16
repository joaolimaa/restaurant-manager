package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.UserDTO;
import fiap.restaurant_manager.application.gateways.UserGateway;
import fiap.restaurant_manager.infrastructure.util.mappers.UserControllerMapper;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Collection;

@AllArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;
    private final UserControllerMapper mapper;

    public Collection<UserDTO> findAllUsers() {
        return userGateway.findAll().stream().map(mapper::toUserDTO).toList();
    }

    public UserDTO findUserById(Long id) {
        return mapper.toUserDTO(userGateway.findById(id));
    }

    public UserDTO createUser(UserDTO userDTO) {
        val userDomain = mapper.toUserDomain(userDTO);
        val userEntity = mapper.toUserEntity(userDomain);
        return mapper.toUserDTO(userGateway.save(userEntity));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        val userDomain = mapper.toUserDomain(findUserById(id));
        val userDomainNew = mapper.toUserDomain(userDTO);
        userDomain.setName(userDomainNew.getName());
        userDomain.setEmail(userDomainNew.getEmail());
        return mapper.toUserDTO(userGateway.save(mapper.toUserEntity(id, userDomain)));
    }

    public void deleteUser(Long id) {
        userGateway.deleteById(id);
    }
}
