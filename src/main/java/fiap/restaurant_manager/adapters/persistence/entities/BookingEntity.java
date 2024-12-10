package fiap.restaurant_manager.adapters.persistence.entities;

import fiap.restaurant_manager.domain.enums.StatusBooking;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restauranteId;
    private Long usuarioId;
    private LocalDateTime dataHora;
    private Integer quantidadePessoas;
    private StatusBooking status;

    public BookingEntity(Long restauranteId, Long usuarioId, LocalDateTime dataHora, Integer quantidadePessoas, StatusBooking status) {
        this.restauranteId = restauranteId;
        this.usuarioId = usuarioId;
        this.dataHora = dataHora;
        this.quantidadePessoas = quantidadePessoas;
        this.status = status;
    }
}
