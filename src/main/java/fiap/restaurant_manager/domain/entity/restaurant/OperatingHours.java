package fiap.restaurant_manager.domain.entity.restaurant;

import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperatingHours {

    @NotNull(message = "O dia da semana não pode ser nulo")
    @Schema(description = "Representa o dia da semana", example = "segunda-feira", required = true)
    private DayOfWeek dayOfWeek;

    @NotNull(message = "O horário de início não pode ser nulo")
    @Schema(description = "Horário de início do funcionamento", example = "2024-11-27T09:00:00+00:00", required = true)
    private ZonedDateTime startTime;

    @NotNull(message = "O horário de término não pode ser nulo")
    @Schema(description = "Horário de término do funcionamento", example = "2024-11-27T17:00:00+00:00", required = true)
    private ZonedDateTime endTime;
}
