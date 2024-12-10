package fiap.restaurant_manager.adapters.persistence.repositoryImpl;

import fiap.restaurant_manager.adapters.persistence.entities.UserEntity;
import fiap.restaurant_manager.adapters.persistence.repository.UserRepository;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper.UserEntityMapper;
import fiap.restaurant_manager.application.gateways.UserGateway;
import fiap.restaurant_manager.domain.entities.User;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserEntityMapper mapper;

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a zona com o ID: " + id + "."));
        return mapper.toDomain(userEntity);
    }

    @Override
    public User cadastrarUsuario(User usuario) {
        UserEntity entity = mapper.toEntity(usuario);
        userRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<User> listarTodos() {
        return userRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public User alterarUsuario(Long id, User usuario) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a zona com o ID: " + id + "."));

        userEntity.setEmail(usuario.getEmail());
        
        userRepository.save(userEntity);
        return mapper.toDomain(userEntity);
    }

    @Override
    public boolean deletarUsuario(Long id) {
        findById(id);
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
