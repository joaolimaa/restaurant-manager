package fiap.restaurant_manager.infra.controller.usuario;

import fiap.restaurant_manager.application.usecase.usuario.AlterarUsuario;
import fiap.restaurant_manager.application.usecase.usuario.CriarUsuario;
import fiap.restaurant_manager.application.usecase.usuario.DeletarUsuario;
import fiap.restaurant_manager.application.usecase.usuario.ListarUsuarios;
import fiap.restaurant_manager.domain.entity.usuario.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {

    private final CriarUsuario criarUsuario;
    private final ListarUsuarios listarUsuarios;
    private final AlterarUsuario alterarUsuario;
    private final DeletarUsuario deletarUsuario;

    public UsuarioController(CriarUsuario criarUsuario, ListarUsuarios listarUsuarios, AlterarUsuario alterarUsuario, DeletarUsuario deletarUsuario) {
        this.criarUsuario = criarUsuario;
        this.listarUsuarios = listarUsuarios;
        this.alterarUsuario = alterarUsuario;
        this.deletarUsuario = deletarUsuario;
    }

    @PostMapping
    public UsuarioDto cadastraUsuario(@RequestBody UsuarioDto dto){
        Usuario usuarioSalvo = criarUsuario.cadastrarUsuario(new Usuario(dto.cpf(), dto.nome(), dto.email()));
        return new UsuarioDto(usuarioSalvo.getCpf(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());

    }

    @PostMapping
    @RequestMapping("/alterar")
    public UsuarioDto alterarUsuario(@RequestBody UsuarioDto dto){
        Usuario usuarioSalvo = alterarUsuario.alterarUsuario(dto.cpf(), dto.nome(), dto.email());
        return new UsuarioDto(usuarioSalvo.getCpf(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());

    }

    @RequestMapping("/deletar")
    public void deletarUsuario(@RequestBody UsuarioDto dto){
        deletarUsuario.deletarUsuario(dto.cpf(), dto.nome(), dto.email());

    }

    @GetMapping
    public List<UsuarioDto> listarUsuarios(){
        return listarUsuarios.listarTodos().stream().map(u -> new UsuarioDto(u.getCpf(), u.getNome(), u.getEmail())).collect(Collectors.toList());
    }
}
