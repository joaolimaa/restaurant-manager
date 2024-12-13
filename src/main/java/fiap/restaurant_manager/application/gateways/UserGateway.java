package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.UserEntity;
import fiap.restaurant_manager.adapters.persistence.repository.UserRepository;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class UserGateway {
    private final UserRepository userRepository;

    public Collection<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário " + id + " não encontrado."));
    }

    public UserEntity save(UserEntity userEntity) {

        return userRepository.save(userEntity);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
