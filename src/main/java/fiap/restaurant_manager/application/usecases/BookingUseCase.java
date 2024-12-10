package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class BookingUseCase {
    private final BookingGateway bookingGateway;

    public Booking atualizaReserva(Booking booking) {
        return bookingGateway.atualizaReserva(booking);
    }

    public Booking atualizaStatusReserva(Long id, StatusBooking statusBooking) {
        return bookingGateway.atualizarStatusReserva(id, statusBooking);
    }

    public Booking findById(Long id) {
        return bookingGateway.findById(id);
    }

    public Collection<Booking> listaTodasAsReservas() {
        return bookingGateway.listarTodos();
    }
}
