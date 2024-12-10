package fiap.restaurant_manager.adapters.persistence.repositoryImpl;

import fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper.BookingEntityMapper;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import fiap.restaurant_manager.domain.exception.ExpectationFailedException;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@AllArgsConstructor
public class BookingRepositoryGateway implements BookingGateway {
    private final BookingRepository bookingRepository;
    private final BookingEntityMapper reservaMapper;

    @Transactional
    @Override
    public Booking atualizaReserva(Booking booking) {
        BookingEntity bookingEntity = reservaMapper.toEntity(booking);

        return reservaMapper.toDomain(bookingRepository.save(bookingEntity));
    }

    @Override
    public Collection<Booking> listarTodos() {
        return bookingRepository.findAll().stream().map(reservaMapper::toDomain).toList();
    }

    @Transactional
    @Override
    public Booking findById(Long id){

        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a zona com o ID: " + id + "."));

        return reservaMapper.toDomain(bookingEntity);
    }

    @Transactional
    @Override
    public Booking atualizarStatusReserva(Long id, StatusBooking statusBooking){
        Booking booking = findById(id);
        StatusBooking statusBookingAtual = booking.getStatus();
        BookingEntity bookingEntity = reservaMapper.toEntity(booking);

        if(statusBookingAtual.equals(StatusBooking.CONFIRMADA) || statusBookingAtual.equals(StatusBooking.PENDENTE)){

            bookingEntity.setStatus(statusBooking);

            validaStatus(bookingEntity);

            return reservaMapper.toDomain(bookingRepository.save(bookingEntity));
        }
        throw new ExpectationFailedException("Essa reserva não pode ser atualizada, pois já está cancelada.");
    }

    private void validaStatus(BookingEntity reserva) {
        try {
            StatusBooking.valueOf(String.valueOf(reserva.getStatus()));
        } catch (Exception e) {
            throw new ExpectationFailedException("Valor diferente de CONFIRMADA ou CANCELADA.");
        }
    }
}
