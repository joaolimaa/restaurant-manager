package fiap.restaurant_manager.adapters.gateway.Usuario;

import fiap.restaurant_manager.domain.entity.usuario.Usuario;

import java.util.List;

public interface RepositorioUsuario {

    Usuario cadastrarUsuario(Usuario usuario);
    List<Usuario> listarTodos();
    Usuario alterarUsuario(String cpf, String nome, String email);
    void deletarUsuario(String cpf, String nome, String email);
}
