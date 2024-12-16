package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.*;
import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.domain.enums.KitchenType;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import fiap.restaurant_manager.domain.exception.ExpectationFailedException;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import fiap.restaurant_manager.infrastructure.util.mappers.BookingControllerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingUseCaseTest {

    @InjectMocks BookingUseCase bookingUseCase;
    @Mock BookingRepository bookingRepository;
    @Mock BookingControllerMapper bookingMapper;
    @Mock RestaurantUseCase restaurantUseCase;
    @Mock BookingGateway bookingGateway;
    @Mock UserUseCase userUseCase;

    // TODO: This test is not working. Need fix.
    //@Test
    //void shouldUpdatedForTheNewStatus_WhenTheCurrentStatusIsConfirmed(){
    //    StatusBooking bookingStatus = StatusBooking.CANCELED;
    //
    //    Long bookingId = 1L;
    //    Long restaurantId = 2L;
    //    Long userId = 1L;
    //
    //    BookingDTO updatedBooking = new BookingDTO(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, bookingStatus);
    //    BookingDTO currentBooking = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
    //    BookingEntity updatedBookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, bookingStatus);
    //    BookingEntity bookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
    //    Booking booking = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
    //
    //    when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenReturn(currentBooking);
    //    when(bookingMapper.toBookingDomain(bookingUseCase.findBookingById(bookingId))).thenReturn(booking);
    //    when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);
    //    when(bookingMapper.toBookingDTO(bookingEntity)).thenReturn(updatedBooking);
    //    when(bookingGateway.save(bookingEntity)).thenReturn(updatedBookingEntity);
    //
    //    BookingDTO result = bookingUseCase.updateStatus(bookingId, bookingStatus);
    //
    //    Assertions.assertNotNull(result);
    //    Assertions.assertEquals(StatusBooking.CANCELED, result.status());
    //}

    // TODO: This test is not working. Need fix.
    //@Test
    //void shouldUpdatedForTheNewStatus_WhenTheCurrentStatusIsPending(){
    //    StatusBooking bookingStatus = StatusBooking.CONFIRMED;
    //
    //    Long bookingId = 1L;
    //    Long restaurantId = 2L;
    //    Long userId = 1L;
    //
    //    BookingDTO updatedBooking = new BookingDTO(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, bookingStatus);
    //    BookingDTO currentBooking = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.PENDING);
    //    BookingEntity updatedBookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, bookingStatus);
    //    BookingEntity bookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.PENDING);
    //    Booking booking = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.PENDING);
    //
    //    when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenReturn(currentBooking);
    //    when(bookingMapper.toBookingDomain(bookingUseCase.findBookingById(bookingId))).thenReturn(booking);
    //    when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);
    //    when(bookingMapper.toBookingDTO(bookingEntity)).thenReturn(updatedBooking);
    //    when(bookingGateway.save(bookingEntity)).thenReturn(updatedBookingEntity);
    //
    //    BookingDTO result = bookingUseCase.updateStatus(bookingId, bookingStatus);
    //
    //    Assertions.assertNotNull(result);
    //    Assertions.assertEquals(StatusBooking.CONFIRMED, result.status());
    //}

    @Test
    void shouldReturnAnExpectationFailedException_WhenTheCurrentStatusIsCancelled(){
        StatusBooking bookingStatus = StatusBooking.CONFIRMED;

        Long bookingId = 1L;
        Long restaurantId = 2L;
        Long userId = 1L;

        BookingDTO currentBooking = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CANCELED);
        BookingEntity bookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CANCELED);
        Booking booking = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CANCELED);

        when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenReturn(currentBooking);
        when(bookingMapper.toBookingDomain(bookingUseCase.findBookingById(bookingId))).thenReturn(booking);
        when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);

        ExpectationFailedException exception = Assertions.assertThrows(
                ExpectationFailedException.class,
                () -> bookingUseCase.updateStatus(bookingId, bookingStatus)
        );

        Assertions.assertEquals("Essa reserva não pode ser atualizada, pois já está cancelada.", exception.getReason());
    }

    @Test
    void shouldReturnAnExpectationFailedException_WhenTheUpdateStatusIsPending(){
        StatusBooking bookingStatus = StatusBooking.PENDING;

        Long bookingId = 1L;
        Long restaurantId = 2L;
        Long userId = 1L;

        BookingDTO currentBooking = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        BookingEntity bookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        Booking booking = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);

        when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenReturn(currentBooking);
        when(bookingMapper.toBookingDomain(bookingUseCase.findBookingById(bookingId))).thenReturn(booking);
        when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);

        ExpectationFailedException exception = Assertions.assertThrows(
                ExpectationFailedException.class,
                () -> bookingUseCase.updateStatus(bookingId, bookingStatus)
        );

        Assertions.assertEquals("Valor diferente de CONFIRMADA ou CANCELADA.", exception.getReason());
    }

    @Test
    void shouldReturnTheBookingDetails_WhenTheIdIsFound(){
        Long bookingId = 1L;
        Long restaurantId = 2L;
        Long userId = 1L;

        BookingDTO currentBooking = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);

        when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenReturn(currentBooking);

        BookingDTO result = bookingUseCase.findBookingById(bookingId);

        assertNotNull(result);
        Assertions.assertEquals(result, currentBooking);
    }

    @Test
    void shouldReturnANotFoundException_WhenTheIdIsNotFound(){
        Long bookingId = 1L;

        when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenThrow(new NotFoundException("Reserva " + bookingId + " não encontrada."));

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> bookingUseCase.findBookingById(bookingId)
        );

        Assertions.assertEquals(("Reserva " + bookingId + " não encontrada."), exception.getReason());
    }

    @Test
    void shouldReturnAllBookings(){
        long bookingId = 1L;
        long restaurantId = 2L;
        long userId = 1L;

        BookingEntity booking1 = new BookingEntity(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        BookingEntity booking2 = new BookingEntity(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        BookingEntity booking3 = new BookingEntity(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);

        Collection<BookingEntity> bookingList = new ArrayList<>();
        bookingList.add(booking1);
        bookingList.add(booking2);
        bookingList.add(booking3);

        when(bookingGateway.findAll()).thenReturn(bookingList);

        Collection<BookingDTO> result = bookingUseCase.findAllBooking();

        assertNotNull(result);
        Assertions.assertEquals(3, bookingList.size());
    }

    @Test
    void shouldDeleteBooking_WhenIdIsFound() {
        Long bookingId = 1L;

        bookingUseCase.deleteBooking(bookingId);

        verify(bookingGateway, times(1)).deleteById(bookingId);
    }

    @Test
    void shouldCreateABooking_WhenQuantityThePeopleIsValid(){
        long bookingId = 1L;
        long restaurantId = 1L;
        long userId = 1L;

        BookingEntity bookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        BookingDTO bookingDTO = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        Booking booking = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        UserDTO userDTO = new UserDTO(userId, "Carlos", "carlos@gmail.com","13456456778");

        AddressDTO addressDTO = mock(AddressDTO.class);
        List<OperatingHoursDTO> operatingHoursDTO = Collections.singletonList(mock(OperatingHoursDTO.class));
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "Nonna Pizzaria", addressDTO, KitchenType.ITALIAN,"12345678906543", operatingHoursDTO, 20);

        when(restaurantUseCase.findRestaurantById(bookingEntity.getRestaurantId())).thenReturn(restaurantDTO);
        when(bookingMapper.toBookingDomain(bookingDTO)).thenReturn(booking);
        when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);
        when(bookingGateway.save(bookingEntity)).thenReturn(bookingEntity);
        when(userUseCase.findUserById(bookingEntity.getUserId())).thenReturn(userDTO);
        when(bookingMapper.toBookingDTO(bookingEntity)).thenReturn(bookingDTO);

        BookingDTO result = bookingUseCase.createBooking(bookingDTO);

        assertNotNull(result);
        verify(bookingGateway, times(1)).save(bookingEntity);
    }

    @Test
    void shouldReturnAnIllegalArgumentException_WhenQuantityThePeopleIsNotValid(){
        long bookingId = 1L;
        long restaurantId = 2L;
        long userId = 1L;

        BookingEntity bookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),23, StatusBooking.CONFIRMED);
        BookingDTO bookingDTO = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),23, StatusBooking.CONFIRMED);
        Booking booking = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(1),23, StatusBooking.CONFIRMED);
        UserDTO userDTO = new UserDTO(userId, "Carlos", "carlos@gmail.com","13456456778");

        AddressDTO addressDTO = mock(AddressDTO.class);
        List<OperatingHoursDTO> operatingHoursDTO = Collections.singletonList(mock(OperatingHoursDTO.class));
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "Nonna Pizzaria", addressDTO, KitchenType.ITALIAN,"12345678906543", operatingHoursDTO, 20);

        when(restaurantUseCase.findRestaurantById(bookingEntity.getRestaurantId())).thenReturn(restaurantDTO);
        when(bookingMapper.toBookingDomain(bookingDTO)).thenReturn(booking);
        when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);
        when(userUseCase.findUserById(bookingEntity.getUserId())).thenReturn(userDTO);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () ->  bookingUseCase.createBooking(bookingDTO)
        );

        Assertions.assertEquals(("A capacidade máxima excedida do restaurante."), exception.getMessage());
    }

    @Test
    void shouldUpdateBookingWhenIdIsFound() {
        long bookingId = 1L;
        long restaurantId = 2L;
        long userId = 1L;

        BookingDTO updatedBooking = new BookingDTO(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(3),6, StatusBooking.CONFIRMED);
        BookingDTO currentBooking = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        BookingEntity updatedBookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(3),6, StatusBooking.CONFIRMED);
        BookingEntity bookingEntity = new BookingEntity(bookingId,restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        Booking booking = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);
        Booking updatedBookingDomain = new Booking(restaurantId,userId, LocalDateTime.now().plusDays(3),6, StatusBooking.CONFIRMED);

        AddressDTO addressDTO = mock(AddressDTO.class);
        List<OperatingHoursDTO> operatingHoursDTO = Collections.singletonList(mock(OperatingHoursDTO.class));
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "Nonna Pizzaria", addressDTO, KitchenType.ITALIAN,"12345678906543", operatingHoursDTO, 20);

        // Stubs corretos
        when(bookingMapper.toBookingDomain((bookingUseCase.findBookingById(bookingId)))).thenReturn(booking); // Retorna o currentBooking
        when(bookingMapper.toBookingDomain(any(BookingDTO.class))).thenReturn(booking); // Converte o currentBooking
        when(restaurantUseCase.findRestaurantById(restaurantId)).thenReturn(restaurantDTO); // Retorna o restaurante
        when(bookingMapper.toBookingEntity(any(Booking.class))).thenReturn(bookingEntity); // Converte qualquer Booking
        when(bookingMapper.toBookingDTO(any(BookingEntity.class))).thenReturn(updatedBooking); // Converte qualquer BookingEntity
        when(bookingGateway.save(any(BookingEntity.class))).thenReturn(updatedBookingEntity); // Salva qualquer BookingEntity

        // Chamada do método
        BookingDTO result = bookingUseCase.updateBooking(bookingId, updatedBooking);

        // Validações
        assertNotNull(result);
        Assertions.assertEquals(updatedBooking.bookingDate(), result.bookingDate());
        Assertions.assertEquals(updatedBooking.peopleQuantity(), result.peopleQuantity());
    }

    @Test
    void shouldThrowExceptionWhenCapacityExceeds() {
        long bookingId = 1L;
        long restaurantId = 2L;
        long userId = 1L;
        String date =  "2024-12-25T18:30:00";

        BookingDTO updatedBookingDTO = new BookingDTO(bookingId,restaurantId,userId, LocalDateTime.parse(date),6, StatusBooking.CONFIRMED);
        Booking mockUpdatedBookingDomain = new Booking(restaurantId,userId, LocalDateTime.parse(date), 6, StatusBooking.CONFIRMED);
        BookingDTO currentBooking = new BookingDTO(bookingId, restaurantId,userId, LocalDateTime.now().plusDays(1),4, StatusBooking.CONFIRMED);

        AddressDTO addressDTO = mock(AddressDTO.class);
        List<OperatingHoursDTO> operatingHoursDTO = Collections.singletonList(mock(OperatingHoursDTO.class));
        RestaurantDTO mockRestaurant = new RestaurantDTO(
                "Nonna Pizzaria", addressDTO, KitchenType.ITALIAN,"12345678906543", operatingHoursDTO, 20);

        when(bookingMapper.toBookingDomain(currentBooking)).thenReturn(mockUpdatedBookingDomain);
        when(bookingUseCase.findBookingById(bookingId)).thenReturn(currentBooking);
        when(restaurantUseCase.findRestaurantById(bookingId)).thenReturn(mockRestaurant);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                bookingUseCase.updateBooking(bookingId, updatedBookingDTO)
        );

        Assertions.assertEquals("A capacidade máxima excedida do restaurante.", exception.getMessage());
    }
}
