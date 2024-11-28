package fiap.restaurant_manager.infra.config;

import fiap.restaurant_manager.adapters.gateway.Usuario.RepositorioUsuario;
import fiap.restaurant_manager.application.usecase.usuario.CriarUsuario;
import fiap.restaurant_manager.application.usecase.usuario.ListarUsuarios;
import fiap.restaurant_manager.infra.database.RepositorioUsuarioJpa;
import fiap.restaurant_manager.infra.database.UsuarioEntityMapper;
import fiap.restaurant_manager.infra.database.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    CriarUsuario criarUsuario(RepositorioUsuario repositorioUsuario) {
        return new CriarUsuario(repositorioUsuario);
    }

    @Bean
    RepositorioUsuarioJpa repositorioUsuarioJpa(UsuarioRepository repositorio, UsuarioEntityMapper mapper) {
        return new RepositorioUsuarioJpa(repositorio, mapper);
    }

    @Bean
    UsuarioEntityMapper usuarioEntityMapper() {
        return new UsuarioEntityMapper();
    }

    @Bean
    ListarUsuarios listaUsuarios(RepositorioUsuario repositorioUsuario) {
        return new ListarUsuarios(repositorioUsuario);
    }


}
