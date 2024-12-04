package fiap.restaurant_manager.infra.database;

import fiap.restaurant_manager.adapters.gateway.Usuario.RepositorioUsuario;
import fiap.restaurant_manager.domain.entity.usuario.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioUsuarioJpa implements RepositorioUsuario {

    private final UsuarioRepository repositorio;
    private final UsuarioEntityMapper mapper;

    public RepositorioUsuarioJpa(UsuarioRepository repositorio, UsuarioEntityMapper mapper){
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        repositorio.save(entity);
        return mapper.toDomain(entity);


    }

    @Override
    public List<Usuario> listarTodos() {
        return repositorio.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Usuario alteraUsuario(String cpf, String nome, String email) {
        UsuarioEntity entity = repositorio.findByCpf(cpf);
        entity.setEmail(email);
        repositorio.save(entity);
        return mapper.toDomain(entity);

    }
}
