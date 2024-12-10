package fiap.restaurant_manager.application.gateways;


import fiap.restaurant_manager.domain.entities.Book;
import fiap.restaurant_manager.domain.enums.StatusBooking;

import java.util.Collection;

public interface BookingGateway {
    Book atualizaReserva(Book book);
    Collection<Book> listarTodos();
    Book findById(Long id);
    Book atualizarStatusReserva(Long id, StatusBooking statusBooking);
}
