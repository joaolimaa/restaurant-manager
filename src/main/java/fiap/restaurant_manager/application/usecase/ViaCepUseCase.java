package fiap.restaurant_manager.application.usecase;

import fiap.restaurant_manager.domain.valueObject.AddressValidationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ViaCepUseCase {
    private final RestTemplate restTemplate;

    public ViaCepUseCase(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AddressValidationResponse validateAddress(String postalCode) {
        try {
            String url = String.format("https://viacep.com.br/ws/%s/json/", postalCode);
            return restTemplate.getForObject(url, AddressValidationResponse.class);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Erro ao validar o endereço com o CEP fornecido: " + postalCode, e);
        }
    }
}
