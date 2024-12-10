package fiap.restaurant_manager.adapters.persistence.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperatingHoursEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O dia da semana não pode ser nulo")
    @Schema(description = "Representa o dia da semana", example = "segunda-feira", requiredMode = Schema.RequiredMode.REQUIRED)
    private DayOfWeek dayOfWeek;

    @NotNull(message = "O horário de início não pode ser nulo")
    @Schema(description = "Horário de início do funcionamento", example = "2024-11-27T09:00:00+00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private ZonedDateTime startTime;

    @NotNull(message = "O horário de término não pode ser nulo")
    @Schema(description = "Horário de término do funcionamento", example = "2024-11-27T17:00:00+00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private ZonedDateTime endTime;
}
