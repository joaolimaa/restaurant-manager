package fiap.restaurant_manager.infra.database;

import fiap.restaurant_manager.domain.enums.StatusReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaEntity {

    @Id
    private Long id;
    private Long restauranteId;
    private Long usuarioId;
    private LocalDateTime dataHora;
    private Integer quantidadePessoas;
    private StatusReserva status;
}
