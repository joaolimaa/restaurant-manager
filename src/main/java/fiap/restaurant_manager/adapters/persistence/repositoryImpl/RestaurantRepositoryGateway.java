package fiap.restaurant_manager.adapters.persistence.repositoryImpl;


import fiap.restaurant_manager.adapters.persistence.entities.RestaurantEntity;
import fiap.restaurant_manager.adapters.persistence.repository.RestaurantRepository;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper.RestaurantEntityMapper;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.application.usecases.ViaCepUseCase;
import fiap.restaurant_manager.domain.entities.Restaurant;
import fiap.restaurant_manager.domain.exception.InvalidAddressException;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static fiap.restaurant_manager.infrastructure.util.formatters.formatCNPJ;
import static fiap.restaurant_manager.infrastructure.util.formatters.formatPostalCode;


@AllArgsConstructor
public class RestaurantRepositoryGateway implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantMapper;
    private final ViaCepUseCase viaCepUseCase;

    @Override
    public List<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll().stream().map(restaurantMapper::toDomain).toList();
    }

    @Override
    public Restaurant findById(Long id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a zona com o ID: " + id + "."));
        return restaurantMapper.toDomain(restaurantEntity);
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {

        var address = restaurant.getAddressEntity();
        var validationResponse = viaCepUseCase.validateAddress(formatPostalCode(address.getPostalCode()));

        if (validationResponse == null) {
            throw new InvalidAddressException();
        }

        // Atualiza o endereço com os dados retornados pela API ViaCEP
        address.setStreet(validationResponse.getLogradouro());
        address.setNeighborhood(validationResponse.getBairro());
        address.setCity(validationResponse.getLocalidade());
        address.setState(validationResponse.getUf());
        restaurant.setCnpj(formatCNPJ(restaurant.getCnpj()));

        return restaurantMapper.toDomain(restaurantRepository.save(restaurantMapper.toEntity(restaurant)));
    }

    @Transactional
    @Override
    public Restaurant update(Long id, Restaurant restaurant) {
        findById(id);
        return restaurantMapper.toDomain(restaurantRepository.save(restaurantMapper.toEntity(restaurant)));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        findById(id);
        try {
            restaurantRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
