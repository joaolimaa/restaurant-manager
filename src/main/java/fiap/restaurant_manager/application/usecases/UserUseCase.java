package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.application.gateways.UserGateway;
import fiap.restaurant_manager.domain.entities.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;

    public  User findUserById(Long id){
        return userGateway.findById(id);
    }
    public User cadastrarUsuario(User usuario){
        return userGateway.cadastrarUsuario(usuario);
    }
    public List<User> listarTodos(){
        return userGateway.listarTodos();
    }
    public User alterarUsuario(Long id, User user){
        return userGateway.alterarUsuario(id, user);
    }
    public boolean deletarUsuario(Long id){
        return userGateway.deletarUsuario(id);
    }

}
