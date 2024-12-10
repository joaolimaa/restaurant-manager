package fiap.restaurant_manager.adapters.persistence.repositoryImpl;

import fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper.BookingEntityMapper;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.domain.entities.Book;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import fiap.restaurant_manager.domain.exception.ExpectationFailedException;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import fiap.restaurant_manager.adapters.persistence.entities.BookEntity;
import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@AllArgsConstructor
public class BookingRepositoryGateway implements BookingGateway {

    private final BookingRepository bookingRepository;
    private final BookingEntityMapper reservaMapper;

    @Transactional
    @Override
    public Book atualizaReserva(Book book) {
        BookEntity bookEntity = reservaMapper.toEntity(book);

        return reservaMapper.toDomain(bookingRepository.save(bookEntity));
    }

    @Override
    public Collection<Book> listarTodos() {
        return bookingRepository.findAll().stream().map(reservaMapper::toDomain).toList();
    }

    @Transactional
    @Override
    public Book findById(Long id){

        BookEntity bookEntity = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a zona com o ID: " + id + "."));

        return reservaMapper.toDomain(bookEntity);
    }

    @Transactional
    @Override
    public Book atualizarStatusReserva(Long id, StatusBooking statusBooking){
        Book book = findById(id);
        StatusBooking statusBookingAtual = book.getStatus();
        BookEntity bookEntity = reservaMapper.toEntity(book);

        if(statusBookingAtual.equals(StatusBooking.CONFIRMADA) || statusBookingAtual.equals(StatusBooking.PENDENTE)){

            bookEntity.setStatus(statusBooking);

            validaStatus(bookEntity);

            return reservaMapper.toDomain(bookingRepository.save(bookEntity));
        }
        throw new ExpectationFailedException("Essa reserva não pode ser atualizada, pois já está cancelada.");
    }

    private void validaStatus(BookEntity reserva) {
        try {
            StatusBooking.valueOf(String.valueOf(reserva.getStatus()));
        } catch (Exception e) {
            throw new ExpectationFailedException("Valor diferente de CONFIRMADA ou CANCELADA.");
        }
    }
}
