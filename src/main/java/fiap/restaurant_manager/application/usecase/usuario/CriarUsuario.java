package fiap.restaurant_manager.application.usecase.usuario;

import fiap.restaurant_manager.adapters.gateway.Usuario.RepositorioUsuario;
import fiap.restaurant_manager.domain.entity.usuario.Usuario;

public class CriarUsuario {

    private final RepositorioUsuario repositorio;

    public CriarUsuario(RepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        return repositorio.cadastrarUsuario(usuario);
    }
}
