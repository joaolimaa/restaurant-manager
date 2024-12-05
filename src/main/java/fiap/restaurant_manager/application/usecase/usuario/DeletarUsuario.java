package fiap.restaurant_manager.application.usecase.usuario;

import fiap.restaurant_manager.adapters.gateway.Usuario.RepositorioUsuario;
import fiap.restaurant_manager.domain.entity.usuario.Usuario;

public class DeletarUsuario {

    private final RepositorioUsuario repositorio;

    public DeletarUsuario(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public void deletarUsuario(String cpf, String nome, String email) {
        repositorio.deletarUsuario(cpf, nome, email);
    }
}
