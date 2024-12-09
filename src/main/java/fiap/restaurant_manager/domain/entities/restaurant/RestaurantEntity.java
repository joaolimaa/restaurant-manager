package fiap.restaurant_manager.domain.entities.restaurant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CNPJ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantEntity {

    @Id
    @Schema(description = "ID do restaurante", example = "1", required = true)
    private Long id;

    @NotEmpty(message = "O nome do restaurante não pode ser vazio")
    @Schema(description = "Nome do restaurante", example = "Restaurante Exemplo", required = true)
    private String name;

    @NotEmpty(message = "A localização não pode ser vazia")
    @Schema(description = "Localização do restaurante", example = "Rua das Flores, 123", required = true)
    private String location;

    @NotEmpty(message = "O tipo de cozinha não pode ser vazio")
    @Schema(description = "Tipo de cozinha oferecido pelo restaurante", example = "Italiana", required = true)
    private String kitchenType;

    @CNPJ(message = "Formato de CNPJ inválido")
    @Schema(description = "CNPJ do restaurante", example = "12.345.678/0001-99", required = true)
    private String cnpj;

    @NotNull(message = "Os horários de funcionamento não podem ser nulos")
    @Schema(description = "Lista de horários de funcionamento para cada dia da semana", required = true)
    private List<OperatingHours> operatingHours;

    @NotNull(message = "A capacidade não pode ser nula")
    @Positive(message = "A capacidade deve ser maior que zero")
    @Schema(description = "Capacidade de pessoas no restaurante", example = "100", required = true)
    private int capacity;
}
