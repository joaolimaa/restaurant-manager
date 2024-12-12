package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class BookingGateway {
    private final BookingRepository bookingRepository;

    public Collection<BookingEntity> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<BookingEntity> findById(Long id){
        return bookingRepository.findById(id);
    }

    public void save(BookingEntity bookingEntity) {
        bookingRepository.save(bookingEntity);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
