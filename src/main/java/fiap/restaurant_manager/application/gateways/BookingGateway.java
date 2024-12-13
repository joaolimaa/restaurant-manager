package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;


import java.util.Collection;


@AllArgsConstructor
public class BookingGateway {
    private final BookingRepository bookingRepository;

    public Collection<BookingEntity> findAll() {
        return bookingRepository.findAll();
    }

    public BookingEntity findById(Long id){
        return bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Reserva " + id + " não encontrada."));
    }

    public BookingEntity save(BookingEntity bookingEntity) {
        return bookingRepository.save(bookingEntity);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
