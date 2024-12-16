package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.domain.entities.Booking;

public class BookingControllerMapper {

    public Booking toBookingDomain(BookingDTO booking) {
        return new Booking(
                booking.restaurantId(),
                booking.userId(),
                booking.bookingDate(),
                booking.peopleQuantity(),
                booking.status());
    }

    public BookingEntity toBookingEntity(Booking booking) {
        return new BookingEntity(
                booking.getRestaurantId(),
                booking.getUserId(),
                booking.getBookingDate(),
                booking.getPeopleQuantity(),
                booking.getStatus());
    }

    public BookingDTO toBookingDTO(BookingEntity booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getRestaurantId(),
                booking.getUserId(),
                booking.getBookingDate(),
                booking.getPeopleQuantity(),
                booking.getStatus()
        );
    }
}
