package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.domain.entities.User;

import java.util.List;

public interface UserGateway {
    User findById(Long id);
    User cadastrarUsuario(User usuario);
    List<User> listarTodos();
    User alterarUsuario(Long id, User usuario);
    boolean deletarUsuario(Long id);
}
