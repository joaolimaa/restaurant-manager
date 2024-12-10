package fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper;


import fiap.restaurant_manager.domain.entities.Book;
import fiap.restaurant_manager.adapters.persistence.entities.BookEntity;

public class BookingEntityMapper {

    public BookEntity toEntity(Book bookDomain) {
        return new BookEntity(
                bookDomain.getRestauranteId(),
                bookDomain.getUsuarioId(),
                bookDomain.getDataHora(),
                bookDomain.getQuantidadePessoas(),
                bookDomain.getStatus());
    }

    public Book toDomain(BookEntity reserva) {
        return new Book(reserva.getRestauranteId(),
                reserva.getUsuarioId(),
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus());
    }
}
