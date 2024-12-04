package fiap.restaurant_manager.application.usecase.usuario;

import fiap.restaurant_manager.adapters.gateway.Usuario.RepositorioUsuario;
import fiap.restaurant_manager.domain.entity.usuario.Usuario;

public class AlterarUsuario {

    private final RepositorioUsuario repositorio;

    public AlterarUsuario(RepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }

    public Usuario alterarUsuario(String cpf, String nome, String email) {
        return repositorio.alteraUsuario(cpf, nome, email);
    }
}
