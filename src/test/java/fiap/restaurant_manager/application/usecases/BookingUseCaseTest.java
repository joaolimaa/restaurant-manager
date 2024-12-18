package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.api.dto.*;
import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.domain.enums.KitchenType;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import fiap.restaurant_manager.domain.exception.ExpectationFailedException;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import fiap.restaurant_manager.infrastructure.util.mappers.BookingControllerMapper;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingUseCaseTest {

    @InjectMocks
    BookingUseCase bookingUseCase;
    @Mock
    BookingControllerMapper bookingMapper;
    @Mock
    RestaurantUseCase restaurantUseCase;
    @Mock
    BookingGateway bookingGateway;

    @Test
    void shouldUpdatedForTheNewStatus_WhenTheCurrentStatusIsConfirmed() {
        val bookingStatus = StatusBooking.CANCELED;
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val updatedBooking = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, bookingStatus);
        val currentBooking = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val updatedBookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, bookingStatus);
        val bookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val booking = new Booking(restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);

        when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenReturn(currentBooking);
        when(bookingMapper.toBookingDomain(bookingUseCase.findBookingById(bookingId))).thenReturn(booking);
        when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);
        when(bookingMapper.toBookingDTO(bookingEntity)).thenReturn(updatedBooking);
        when(bookingGateway.save(bookingEntity)).thenReturn(updatedBookingEntity);

        val result = bookingUseCase.updateStatus(bookingId, bookingStatus);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(StatusBooking.CANCELED, result.status());
    }

    @Test
    void shouldUpdatedForTheNewStatus_WhenTheCurrentStatusIsPending() {
        val bookingStatus = StatusBooking.CONFIRMED;
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;

        val fixedDateTime = LocalDateTime.of(2024, 12, 19, 15, 0); // Data fixa
        val bookingEntity = new BookingEntity(bookingId, restaurantId, userId, fixedDateTime, 4, StatusBooking.PENDING);
        val updatedBookingEntity = new BookingEntity(bookingId, restaurantId, userId, fixedDateTime, 4, bookingStatus);
        val booking = new Booking(restaurantId, userId, fixedDateTime, 4, StatusBooking.PENDING);

        // Ajustar o stubbing para evitar problemas com ArgumentMatchers nas datas
        when(bookingMapper.toBookingDTO(any(BookingEntity.class))).thenAnswer(invocation -> {
            BookingEntity entity = invocation.getArgument(0);
            return new BookingDTO(entity.getId(), entity.getRestaurantId(), entity.getUserId(), entity.getBookingDate(), entity.getPeopleQuantity(), entity.getStatus());
        });

        when(bookingMapper.toBookingDomain(any(BookingDTO.class))).thenReturn(booking);
        when(bookingMapper.toBookingEntity(any(Booking.class))).thenReturn(bookingEntity);
        when(bookingGateway.findById(bookingId)).thenReturn(bookingEntity);
        when(bookingGateway.save(any(BookingEntity.class))).thenReturn(updatedBookingEntity);

        val result = bookingUseCase.updateStatus(bookingId, bookingStatus);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(StatusBooking.CONFIRMED, result.status());
    }


    @Test
    void shouldReturnAnExpectationFailedException_WhenTheCurrentStatusIsCancelled() {
        val bookingStatus = StatusBooking.CONFIRMED;
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val currentBooking = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CANCELED);
        val bookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CANCELED);
        val booking = new Booking(restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CANCELED);

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
    void shouldReturnAnExpectationFailedException_WhenTheUpdateStatusIsPending() {
        val bookingStatus = StatusBooking.PENDING;
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val currentBooking = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val bookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val booking = new Booking(restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);

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
    void shouldReturnTheBookingDetails_WhenTheIdIsFound() {
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val currentBooking = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);

        when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenReturn(currentBooking);

        val result = bookingUseCase.findBookingById(bookingId);
        assertNotNull(result);
        Assertions.assertEquals(result, currentBooking);
    }

    @Test
    void shouldReturnANotFoundException_WhenTheIdIsNotFound() {
        val bookingId = 1L;

        when(bookingMapper.toBookingDTO(bookingGateway.findById(bookingId))).thenThrow(new NotFoundException("Reserva " + bookingId + " não encontrada."));

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> bookingUseCase.findBookingById(bookingId)
        );

        Assertions.assertEquals(("Reserva " + bookingId + " não encontrada."), exception.getReason());
    }

    @Test
    void shouldReturnAllBookings() {
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val booking1 = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val booking2 = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val booking3 = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);

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
        val bookingId = 1L;
        bookingUseCase.deleteBooking(bookingId);
        verify(bookingGateway, times(1)).deleteById(bookingId);
    }

    @Test
    void shouldCreateABooking_WhenQuantityThePeopleIsValid() {
        val bookingId = 1L;
        val restaurantId = 1L;
        val userId = 1L;
        val bookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val bookingDTO = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val booking = new Booking(restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val restaurantDTO = buildMockRestaurantDTO();

        when(restaurantUseCase.findRestaurantById(bookingEntity.getRestaurantId())).thenReturn(restaurantDTO);
        when(bookingMapper.toBookingDomain(bookingDTO)).thenReturn(booking);
        when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);
        when(bookingGateway.save(bookingEntity)).thenReturn(bookingEntity);
        when(bookingMapper.toBookingDTO(bookingEntity)).thenReturn(bookingDTO);

        val result = bookingUseCase.createBooking(bookingDTO);

        assertNotNull(result);
        verify(bookingGateway, times(1)).save(bookingEntity);
    }

    @Test
    void shouldReturnAnIllegalArgumentException_WhenQuantityThePeopleIsNotValid() {
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val bookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 23, StatusBooking.CONFIRMED);
        val bookingDTO = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 23, StatusBooking.CONFIRMED);
        val booking = new Booking(restaurantId, userId, LocalDateTime.now().plusDays(1), 23, StatusBooking.CONFIRMED);
        val restaurantDTO = buildMockRestaurantDTO();

        when(restaurantUseCase.findRestaurantById(bookingEntity.getRestaurantId())).thenReturn(restaurantDTO);
        when(bookingMapper.toBookingDomain(bookingDTO)).thenReturn(booking);
        when(bookingMapper.toBookingEntity(booking)).thenReturn(bookingEntity);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> bookingUseCase.createBooking(bookingDTO)
        );

        Assertions.assertEquals(("A capacidade máxima excedida do restaurante."), exception.getMessage());
    }

    @Test
    void shouldUpdateBookingWhenIdIsFound() {
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val updatedBooking = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(3), 6, StatusBooking.CONFIRMED);
        val currentBooking = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val updatedBookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(3), 6, StatusBooking.CONFIRMED);
        val bookingEntity = new BookingEntity(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val booking = new Booking(restaurantId, userId, LocalDateTime.now().plusDays(1), 4, StatusBooking.CONFIRMED);
        val restaurantDTO = buildMockRestaurantDTO();

        when(bookingUseCase.findBookingById(bookingId)).thenReturn(currentBooking);
        when(bookingMapper.toBookingDomain(any(BookingDTO.class))).thenReturn(booking);
        when(restaurantUseCase.findRestaurantById(restaurantId)).thenReturn(restaurantDTO);
        when(bookingMapper.toBookingEntity(any(Booking.class))).thenReturn(bookingEntity);
        when(bookingMapper.toBookingDTO(any(BookingEntity.class))).thenReturn(updatedBooking);
        when(bookingGateway.save(any(BookingEntity.class))).thenReturn(updatedBookingEntity);

        // Chamada do método
        val result = bookingUseCase.updateBooking(bookingId, updatedBooking);

        // Validações
        assertNotNull(result);
        Assertions.assertEquals(updatedBooking.bookingDate(), result.bookingDate());
        Assertions.assertEquals(updatedBooking.peopleQuantity(), result.peopleQuantity());
    }

    @Test
    void shouldThrowExceptionWhenCapacityExceeds() {
        val bookingId = 1L;
        val restaurantId = 2L;
        val userId = 1L;
        val booking = new Booking(restaurantId, userId, LocalDateTime.now().plusDays(1), 30, StatusBooking.CONFIRMED);
        val bookingDTO = new BookingDTO(bookingId, restaurantId, userId, LocalDateTime.now().plusDays(3), 6, StatusBooking.CONFIRMED);
        val restaurantDTO = buildMockRestaurantDTO();

        when(bookingUseCase.findBookingById(bookingId)).thenReturn(bookingDTO);
        when(bookingMapper.toBookingDomain(any(BookingDTO.class))).thenReturn(booking);
        when(restaurantUseCase.findRestaurantById(restaurantId)).thenReturn(restaurantDTO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                bookingUseCase.updateBooking(bookingId, bookingDTO)
        );

        Assertions.assertEquals("A capacidade máxima excedida do restaurante.", exception.getMessage());
    }

    private RestaurantDTO buildMockRestaurantDTO() {
        return new RestaurantDTO("Nonna Pizzaria",
                "74375560",
                "Rua teste",
                "Numero 490",
                KitchenType.ITALIAN,
                "12345678906543",
                10,
                LocalTime.parse("08:10:00"),
                LocalTime.parse("18:10:00")
        );
    }
}
