package fiap.restaurant_manager.adapters.api.controllers;

import fiap.restaurant_manager.adapters.api.dto.UserDTO;
import fiap.restaurant_manager.adapters.api.mapper.UserControllerMapper;
import fiap.restaurant_manager.application.usecases.UserUseCase;
import fiap.restaurant_manager.adapters.persistence.entities.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase userUseCase;
    private final UserControllerMapper mapper;

    @Operation(summary = "Coleta todos os usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<UserDTO> findAllRestaurants() {
        return userUseCase.listarTodos().stream().map(mapper::toDTO).toList();
    }

    @Operation(summary = "Procura um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findRestaurantById(@PathVariable Long id) {
        val user = userUseCase.findUserById(id);
        return user != null ? ResponseEntity.ok(mapper.toDTO(user)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cria um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
        val createdUser = userUseCase.cadastrarUsuario(mapper.toUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(createdUser));
    }

    @Operation(summary = "Atualiza usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO user) {
        val updatedUser = userUseCase.alterarUsuario(id, mapper.toUser(user));
        return updatedUser != null ? ResponseEntity.ok(mapper.toDTO(updatedUser)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deleta usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        val isDeleted = userUseCase.deletarUsuario(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
