package fiap.restaurant_manager.application.gateways;


import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.domain.enums.StatusBooking;

import java.util.Collection;

public interface BookingGateway {
    Booking atualizaReserva(Booking booking);
    Collection<Booking> listarTodos();
    Booking findById(Long id);
    Booking atualizarStatusReserva(Long id, StatusBooking statusBooking);
}
