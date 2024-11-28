package fiap.restaurant_manager.application.usecase.usuario;

import fiap.restaurant_manager.adapters.gateway.Usuario.RepositorioUsuario;
import fiap.restaurant_manager.domain.entity.usuario.Usuario;

import java.util.List;

public class ListarUsuarios {
    private final RepositorioUsuario repositorio;


    public ListarUsuarios(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public List<Usuario> listarTodos() {
        return this.repositorio.listarTodos();
    }
}
