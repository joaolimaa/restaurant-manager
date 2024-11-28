package fiap.restaurant_manager.infra.controller.usuario;

import fiap.restaurant_manager.application.usecase.usuario.CriarUsuario;
import fiap.restaurant_manager.application.usecase.usuario.ListarUsuarios;
import fiap.restaurant_manager.domain.entity.usuario.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuario criarUsuario;
    private final ListarUsuarios listarUsuarios;

    public UsuarioController(CriarUsuario criarUsuario, ListarUsuarios listarUsuarios) {
        this.criarUsuario = criarUsuario;
        this.listarUsuarios = listarUsuarios;
    }

    @PostMapping
    public UsuarioDto cadastraUsuario(@RequestBody UsuarioDto dto){
        Usuario usuarioSalvo = criarUsuario.cadastrarUsuario(new Usuario(dto.cpf(), dto.nome(), dto.email()));
        return new UsuarioDto(usuarioSalvo.getCpf(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());

    }

    @GetMapping
    public List<UsuarioDto> listarUsuarios(){
        return listarUsuarios.listarTodos().stream().map(u -> new UsuarioDto(u.getCpf(), u.getNome(), u.getEmail())).collect(Collectors.toList());
    }
}
