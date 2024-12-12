package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class BookingUseCase {
    private final BookingGateway bookingGateway;

    public Collection<BookingEntity> findAllBooking() {
        return bookingGateway.findAll();
    }

    public void createBooking(BookingEntity bookingEntity) {
        bookingGateway.save(bookingEntity);
    }

    public void updateBooking(Long id, BookingEntity bookingEntity) {
        findBookingById(id);
        bookingGateway.save(bookingEntity);
    }

    public void deleteBooking(Long id) {
        bookingGateway.deleteById(id);
    }

    public Optional<BookingEntity> findBookingById(Long id) {
        return bookingGateway.findById(id);
    }
}
