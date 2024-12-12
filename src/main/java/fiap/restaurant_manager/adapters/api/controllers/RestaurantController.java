package fiap.restaurant_manager.adapters.api.controllers;

import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.adapters.api.mapper.RestaurantControllerMapper;
import fiap.restaurant_manager.application.usecases.RestaurantUseCase;
import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
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
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantUseCase restaurantUseCase;
    private final RestaurantControllerMapper mapper;

    @Operation(summary = "Coleta todos os restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<RestaurantDTO> findAllRestaurants() {
        return restaurantUseCase.findAllRestaurants().stream().map(mapper::toDTO).toList();
    }

    @Operation(summary = "Procura um restaurante pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantEntity.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Long id) {
        val restaurant = restaurantUseCase.findRestaurantById(id);
        return restaurant != null ? ResponseEntity.ok(mapper.toDTO(restaurant)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cria um novo restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDto) {
        val createdRestaurant = restaurantUseCase.createRestaurant(mapper.toRestaurant(restaurantDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(createdRestaurant));
    }

    @Operation(summary = "Atualiza restaurante existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long id, @Valid @RequestBody RestaurantDTO restaurantDto) {
        val updatedRestaurant = restaurantUseCase.updateRestaurant(id, mapper.toRestaurant(restaurantDto));
        return updatedRestaurant != null ? ResponseEntity.ok(mapper.toDTO(updatedRestaurant)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deleta restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        val isDeleted = restaurantUseCase.deleteRestaurant(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
