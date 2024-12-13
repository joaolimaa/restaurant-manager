package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.infrastructure.util.mappers.BookingControllerMapper;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Collection;


@AllArgsConstructor
public class BookingUseCase {
    private final BookingGateway bookingGateway;
    private final BookingControllerMapper mapper;


    public BookingDTO findBookingById(Long id) {
        return mapper.toBookingDTO(bookingGateway.findById(id));
    }

    public Collection<BookingDTO> findAllBooking() {
        return bookingGateway.findAll().stream().map(mapper::toBookingDTO).toList();
    }

    public BookingDTO createBooking(BookingDTO booking) {

        val bookingDomain = mapper.toBookingDomain(booking);
        val bookingEntity = mapper.toBookingEntity(bookingDomain);

        //TODO: Verificar o ID Restaurant
        //TODO: Verificar o  ID User

        return mapper.toBookingDTO(bookingGateway.save(bookingEntity));
    }

    public BookingDTO updateBooking(Long id, BookingDTO booking) {

        val bookingDomain = mapper.toBookingDomain(findBookingById(id));
        val bookingDomainNew = mapper.toBookingDomain(booking);

        bookingDomain.setBookingDate(bookingDomainNew.getBookingDate());
        bookingDomain.setPeopleQuantity(bookingDomainNew.getPeopleQuantity());

        return mapper.toBookingDTO(bookingGateway.save(mapper.toBookingEntity(bookingDomain)));
    }

    public void deleteBooking(Long id) {
        bookingGateway.deleteById(id);
    }


}
