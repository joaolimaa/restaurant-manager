package fiap.restaurant_manager.application.usecases.bookingUseCases;

import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.domain.entities.Book;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import lombok.AllArgsConstructor;

import java.util.Collection;


@AllArgsConstructor
public class BookingUseCase {

    private final BookingGateway bookingGateway;

    public Book atualizaReserva(Book book) {
        return bookingGateway.atualizaReserva(book);
    }

    public Book atualizaStatusReserva(Long id, StatusBooking statusBooking) {
        return bookingGateway.atualizarStatusReserva(id, statusBooking);
    }

    public Book findById(Long id) {
        return bookingGateway.findById(id);
    }

    public Collection<Book> listaTodasAsReservas() {
        return bookingGateway.listarTodos();
    }
}
