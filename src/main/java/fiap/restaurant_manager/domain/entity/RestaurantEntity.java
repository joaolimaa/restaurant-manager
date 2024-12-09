package fiap.restaurant_manager.domain.entity;

import fiap.restaurant_manager.domain.valueObject.Address;
import fiap.restaurant_manager.domain.valueObject.OperatingHours;
import jakarta.validation.constraints.NotBlank;
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
    @Schema(description = "ID do restaurante", example = "1")
    private Long id;

    @NotBlank(message = "O nome do restaurante não pode ser vazio")
    @Schema(description = "Nome do restaurante", example = "Restaurante Exemplo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "Os dados do endereço devem ser enviados")
    @Schema(description = "Localização do restaurante",
            example = "{\n" +
                    "  \"postalCode\": \"01153-000\",\n" +
                    "  \"city\": \"São Paulo\",\n" +
                    "  \"state\": \"SP\",\n" +
                    "  \"neighborhood\": \"Centro\",\n" +
                    "  \"street\": \"Rua São Paulo\",\n" +
                    "  \"number\": \"45\"\n" +
                    "}",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Address address;

    @NotBlank(message = "O tipo de cozinha não pode ser vazio")
    @Schema(description = "Tipo de cozinha oferecido pelo restaurante", example = "Italiana", requiredMode = Schema.RequiredMode.REQUIRED)
    private String kitchenType;

    @CNPJ(message = "Formato de CNPJ inválido")
    @NotBlank(message = "O CNPJ não pode ser vazio")
    @Schema(description = "CNPJ do restaurante", example = "12.345.678/0001-99", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cnpj;

    @NotNull(message = "A lista com os horários deve ser enviada")
    @NotEmpty(message = "A lista com os horários não pode estar vazia")
    @Schema(description = "Lista de horários de funcionamento para cada dia da semana",
            example = "[\n" +
                    "  {\n" +
                    "    dayOfWeek: \"segunda-feira\",\n" +
                    "    startTime: \"2024-11-27T09:00:00+00:00\",\n" +
                    "    endTime: \"2024-11-27T17:00:00+00:00\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    dayOfWeek: \"terça-feira\",\n" +
                    "    startTime: \"2024-11-27T09:00:00+00:00\",\n" +
                    "    endTime: \"2024-11-27T17:00:00+00:00\"\n" +
                    "  }\n" +
                    "]",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private List<OperatingHours> operatingHours;

    @NotNull(message = "A capacidade não pode ser nula")
    @Positive(message = "A capacidade deve ser maior que zero")
    @Schema(description = "Capacidade de pessoas no restaurante", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private int capacity;
}
