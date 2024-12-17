package fiap.restaurant_manager.domain.entities;

import fiap.restaurant_manager.domain.enums.StatusBooking;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    private Long restaurantId;
    private Long userId;
    private LocalDateTime bookingDate;
    private Integer peopleQuantity;
    private StatusBooking status;

    public Booking(Long restaurantId, Long userId, LocalDateTime bookingDate, Integer peopleQuantity, StatusBooking status) {

        performsValidations(restaurantId, userId, bookingDate, peopleQuantity, status);

        this.restaurantId = restaurantId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.peopleQuantity = peopleQuantity;
        this.status = status;
    }

    private void performsValidations(Long restaurantId, Long userId, LocalDateTime bookingDate, Integer peopleQuantity, StatusBooking status) {
        //Valida se restaurante esta vazio
        validateRestaurantBookingID(restaurantId);

        //Valida se usuario esta vazio
        validateUserIDBooking(userId);

        //Valida se a hora é valida
        validateBookingDate(bookingDate);

        //Realiza a validação da quantidade de pessoas
        validateQuantityOfPeopleBooking(peopleQuantity);

        //Valida se o status é válido
        validateBookingStatus(String.valueOf(status));
    }

    private void validateRestaurantBookingID(Long restaurantId) {
        if (restaurantId == null || restaurantId <= 0) {
            throw new IllegalArgumentException("O ID do restaurante deve ser um número válido e maior que zero.");
        }
    }

    private void validateUserIDBooking(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("O ID do usuário deve ser um número válido e maior que zero.");
        }
    }

    private void validateBookingStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("O status não pode ser nulo.");
        }

        try {
            StatusBooking.valueOf(status);
        } catch (Exception e) {
            throw new IllegalArgumentException("Valor não é válido para o status.");
        }
    }

    private void validateQuantityOfPeopleBooking(Integer peopleQuantity) {
        if (peopleQuantity < 0) {
            throw new IllegalArgumentException("A quantidade de pessoa tem que ser maior que 0, para realizar a reserva.");
        }
    }

    private void validateBookingDate(LocalDateTime bookingDate) {
        if (bookingDate == null) {
            throw new IllegalArgumentException("A data e hora da reserva não podem ser nulas.");
        }

        if (bookingDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data e hora da reserva devem ser no futuro.");
        }
    }
}