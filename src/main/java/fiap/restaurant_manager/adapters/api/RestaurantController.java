package fiap.restaurant_manager.adapters.api;

import fiap.restaurant_manager.application.usecase.RestaurantUseCase;
import fiap.restaurant_manager.domain.entity.RestaurantEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantUseCase restaurantUseCase;

    public RestaurantController(RestaurantUseCase restaurantUseCase) {
        this.restaurantUseCase = restaurantUseCase;
    }

    @Operation(summary = "Coleta todos os restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<RestaurantEntity> findAllRestaurants() {
        return restaurantUseCase.findAllRestaurants();
    }

    @Operation(summary = "Procura um restaurante pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantEntity.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantEntity> findRestaurantById(@PathVariable Long id) {
        val restaurant = restaurantUseCase.findRestaurantById(id);
        return restaurant != null ? ResponseEntity.ok(restaurant) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cria um novo restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<RestaurantEntity> createRestaurant(@Valid @RequestBody RestaurantEntity restaurant) {
        val createdRestaurant = restaurantUseCase.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
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
    public ResponseEntity<RestaurantEntity> updateRestaurant(@PathVariable Long id, @Valid @RequestBody RestaurantEntity restaurant) {
        val updatedRestaurant = restaurantUseCase.updateRestaurant(id, restaurant);
        return updatedRestaurant != null ? ResponseEntity.ok(updatedRestaurant) : ResponseEntity.notFound().build();
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
