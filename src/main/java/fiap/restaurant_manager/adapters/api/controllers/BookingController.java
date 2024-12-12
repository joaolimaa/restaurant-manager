package fiap.restaurant_manager.adapters.api.controllers;

import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.adapters.api.mapper.BookingControllerMapper;
import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.application.usecases.BookingUseCase;
import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.domain.enums.StatusBooking;
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

import java.util.Collection;

@RestController
@RequestMapping("/v1/api/reserva")
@RequiredArgsConstructor
public class BookingController {
    private final BookingUseCase bookingUseCase;
    private final BookingControllerMapper mapper;

    @GetMapping
    public Collection<BookingDTO> findAllBooking() {
        return bookingUseCase.listaTodasAsReservas().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public BookingDTO findBookingById(@PathVariable Long id){
        return mapper.toDTO(bookingUseCase.findBookingById(id));
    }

    @Operation(summary = "Cria uma nova reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<RestaurantDTO> createBooking(@Valid @RequestBody BookingDTO restaurantDto) {
        val createdBooking = bookingUseCase.createBooking(mapper.toDTO(restaurantDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(createdRestaurant));
    }

    @PutMapping("/{id}")
    public BookingDTO update(@PathVariable String id, @Valid @RequestBody BookingDTO request) {
        Booking booking = mapper.toReserva(request);
        return mapper.toDTO(bookingUseCase.atualizaReserva(booking));
    }

}
