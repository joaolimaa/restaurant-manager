package fiap.restaurant_manager.adapters.persistence.entities;

import fiap.restaurant_manager.domain.enums.StatusBooking;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long restaurantId;

    private Long userId;

    private LocalDateTime bookingDate;

    private Integer peopleQuantity;

    private StatusBooking status;

    public BookingEntity(Long restaurantId, Long userId, LocalDateTime bookingDate, Integer peopleQuantity, StatusBooking status) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.peopleQuantity = peopleQuantity;
        this.status = status;
    }
}
