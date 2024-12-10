package fiap.restaurant_manager.adapters.api.controllers;

import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.adapters.api.mapper.BookingControllerMapper;
import fiap.restaurant_manager.application.usecases.bookingUseCases.BookingUseCase;
import fiap.restaurant_manager.domain.entities.Book;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/api/reserva")
@RequiredArgsConstructor
public class BookingController {
    private final BookingUseCase bookingUseCase;
    private final BookingControllerMapper mapper;

    @GetMapping
    public Collection<BookingDTO> listarTodasAsReservas() {
        return bookingUseCase.listaTodasAsReservas().stream().map(mapper::toDTO).toList();
    }

    @PutMapping("/{id}")
    public BookingDTO update(@PathVariable String id, @Valid @RequestBody BookingDTO request) {
        Book book = mapper.toReserva(request);
        return mapper.toDTO(bookingUseCase.atualizaReserva(book));
    }

    @PutMapping("/{id}/statusReserva")
    public BookingDTO atualizarStatusReserva(@PathVariable Long id, @Valid @RequestBody StatusBooking statusBooking) {
        return mapper.toDTO(bookingUseCase.atualizaStatusReserva(id, statusBooking));
    }

    @GetMapping("/{id}")
    public BookingDTO encontrarReservaPeloId(@PathVariable Long id){
        return mapper.toDTO(bookingUseCase.findById(id));
    }
}
