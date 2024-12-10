package fiap.restaurant_manager.adapters.api.mapper;


import fiap.restaurant_manager.adapters.api.dto.BookDTO;
import fiap.restaurant_manager.domain.entities.Book;

public class BookControllerMapper {

    public Book toReserva(BookDTO reserva) {
        return new Book(
                reserva.restauranteId(),
                reserva.usuarioId(),
                reserva.dataHora(),
                reserva.quantidadePessoas(),
                reserva.status());
    }

    public BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getRestauranteId(),
                book.getUsuarioId(),
                book.getDataHora(),
                book.getQuantidadePessoas(),
                book.getStatus()
        );
    }


}
