package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import fiap.restaurant_manager.domain.enums.ValidBookingStatus;
import fiap.restaurant_manager.domain.exception.ExpectationFailedException;
import fiap.restaurant_manager.infrastructure.util.mappers.BookingControllerMapper;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Collection;


@AllArgsConstructor
public class BookingUseCase {

    private final BookingGateway bookingGateway;
    private final BookingControllerMapper mapper;
    private final RestaurantUseCase restaurantUseCase;
    private final UserUseCase userUseCase;

    public BookingDTO findBookingById(Long id) {
        return mapper.toBookingDTO(bookingGateway.findById(id));
    }

    public Collection<BookingDTO> findAllBooking() {
        return bookingGateway.findAll().stream().map(mapper::toBookingDTO).toList();
    }

    public BookingDTO createBooking(BookingDTO booking) {

        val bookingDomain = mapper.toBookingDomain(booking);
        val bookingEntity = mapper.toBookingEntity(bookingDomain);

        val restaurant = restaurantUseCase.findRestaurantById(bookingEntity.getRestaurantId());
        val user = userUseCase.findUserById(bookingEntity.getUserId());

        validCapacityRestaurant(bookingDomain, restaurant);

        return mapper.toBookingDTO(bookingGateway.save(bookingEntity));
    }

    public BookingDTO updateBooking(Long id, BookingDTO booking) {

        val bookingDomain = mapper.toBookingDomain(findBookingById(id));
        val bookingDomainNew = mapper.toBookingDomain(booking);

        bookingDomain.setBookingDate(bookingDomainNew.getBookingDate());

        val restaurant = restaurantUseCase.findRestaurantById(bookingDomain.getRestaurantId());

        validCapacityRestaurant(bookingDomainNew, restaurant);

        bookingDomain.setPeopleQuantity(bookingDomainNew.getPeopleQuantity());

        return mapper.toBookingDTO(bookingGateway.save(mapper.toBookingEntity(bookingDomain)));
    }

    public BookingDTO updateStatus(Long id, StatusBooking statusBooking) {
        val bookingDomain = mapper.toBookingDomain(findBookingById(id));
        StatusBooking currentStatusBooking = bookingDomain.getStatus();

        val bookingEntity = mapper.toBookingEntity(bookingDomain);

        if (currentStatusBooking.equals(StatusBooking.CONFIRMED) || currentStatusBooking.equals(StatusBooking.PENDING)) {

            bookingEntity.setStatus(statusBooking);

            validateStatus(bookingEntity);

            return mapper.toBookingDTO(bookingGateway.save(bookingEntity));
        }

        throw new ExpectationFailedException("Essa reserva não pode ser atualizada, pois já está cancelada.");
    }

    public void deleteBooking(Long id) {
        bookingGateway.deleteById(id);
    }

    private static void validateStatus(BookingEntity booking) {
        try {
            ValidBookingStatus.valueOf(String.valueOf(booking.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new ExpectationFailedException("Valor diferente de CONFIRMADA ou CANCELADA.");
        }
    }

    private static void validCapacityRestaurant(Booking booking, RestaurantDTO restaurant) {
        if (booking.getPeopleQuantity() > restaurant.capacity()) {
            throw new IllegalArgumentException("A capacidade máxima excedida do restaurante.");
        }
    }

}
