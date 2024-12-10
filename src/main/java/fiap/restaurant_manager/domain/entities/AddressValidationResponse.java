package fiap.restaurant_manager.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressValidationResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private Boolean erro;


}
