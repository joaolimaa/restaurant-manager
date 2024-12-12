package fiap.restaurant_manager.adapters.api.dto;

import fiap.restaurant_manager.domain.enums.StatusBooking;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

public record BookingDTO(@ReadOnlyProperty Long id,
                         Long restaurantId,
                         Long userId,
                         LocalDateTime bookingDate,
                         Integer peopleQuantity,
                         StatusBooking status) {}
