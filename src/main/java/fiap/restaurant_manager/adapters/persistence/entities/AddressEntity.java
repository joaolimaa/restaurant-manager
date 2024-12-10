package fiap.restaurant_manager.adapters.persistence.entities;

import fiap.restaurant_manager.domain.validator.ValidCep;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "É obrigatório o envio do CEP")
    @ValidCep(message = "O CEP fornecido não é válido")
    @Schema(description = "CEP", example = "01153-000", requiredMode = Schema.RequiredMode.REQUIRED)
    private String postalCode;

    @NotBlank(message = "É obrigatório o envio da cidade")
    @Schema(description = "Cidade", example = "São Paulo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String city;

    @NotBlank(message = "É obrigatório o envio da UF")
    @Size(max = 2)
    @Schema(description = "Estado", example = "SP", requiredMode = Schema.RequiredMode.REQUIRED)
    private String state;

    @NotBlank(message = "É obrigatório o envio do bairro")
    @Schema(description = "Bairro", example = "Rua São Paulo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String neighborhood;

    @NotBlank(message = "É obrigatório o envio da rua")
    @Size(max = 80)
    @Schema(description = "Rua, Avenida, Alameda", example = "Rua São Paulo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String street;

    @NotBlank(message = "É obrigatório o envio do número")
    @Schema(description = "Número", example = "45", requiredMode = Schema.RequiredMode.REQUIRED)
    private String number;
}
