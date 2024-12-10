package fiap.restaurant_manager.adapters.api.mapper;


import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.domain.entities.Book;

public class BookingControllerMapper {

    public Book toReserva(BookingDTO reserva) {
        return new Book(
                reserva.restauranteId(),
                reserva.usuarioId(),
                reserva.dataHora(),
                reserva.quantidadePessoas(),
                reserva.status());
    }

    public BookingDTO toDTO(Book book) {
        return new BookingDTO(
                book.getRestauranteId(),
                book.getUsuarioId(),
                book.getDataHora(),
                book.getQuantidadePessoas(),
                book.getStatus()
        );
    }


}
