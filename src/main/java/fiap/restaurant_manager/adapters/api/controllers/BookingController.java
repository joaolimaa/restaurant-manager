package fiap.restaurant_manager.adapters.api.controllers;

import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.application.usecases.BookingUseCase;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/api/reserva")
@RequiredArgsConstructor
public class BookingController {
    private final BookingUseCase bookingUseCase;


    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> findBookingById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookingUseCase.findBookingById(id));
    }
    
    @GetMapping
    public ResponseEntity<Collection<BookingDTO>> findAllBooking() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingUseCase.findAllBooking());
    }
    
    @Operation(summary = "Cria uma nova reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookingEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        bookingUseCase.createBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> update(@PathVariable Long id, @Valid @RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingUseCase.updateBooking(id, bookingDTO));
    }

    @PutMapping("muda-status/{id}")
    public ResponseEntity<BookingDTO> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusBooking statusBooking) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingUseCase.updateStatus(id, statusBooking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookingUseCase.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

}
