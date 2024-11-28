package fiap.restaurant_manager.infra.database;

import fiap.restaurant_manager.domain.entity.usuario.Usuario;

public class UsuarioEntityMapper {

    public UsuarioEntity toEntity(Usuario usuario){
        return new UsuarioEntity(usuario.getCpf(), usuario.getNome(), usuario.getEmail());
    }

    public Usuario toDomain(UsuarioEntity entity){
        return new Usuario(entity.getCpf(), entity.getNome(), entity.getEmail());
    }
}

