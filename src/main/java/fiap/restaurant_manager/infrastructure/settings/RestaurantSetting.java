package fiap.restaurant_manager.infrastructure.settings;

import fiap.restaurant_manager.adapters.persistence.repository.RestaurantRepository;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.RestaurantRepositoryGateway;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper.RestaurantEntityMapper;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.application.usecases.ViaCepUseCase;
import fiap.restaurant_manager.application.usecases.restaurantUseCases.RestaurantUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestaurantSetting {

    @Bean
    RestaurantEntityMapper restaurantEntityMapper(){
        return new RestaurantEntityMapper();
    }
    @Bean
    ViaCepUseCase viaCepUseCase(RestTemplate restTemplate){
        return new ViaCepUseCase(restTemplate);
    }
    @Bean
    RestaurantRepositoryGateway restaurantRepositoryGateway(RestaurantRepository restaurantRepository, RestaurantEntityMapper restaurantMapper, ViaCepUseCase viaCepUseCase){
        return new RestaurantRepositoryGateway(restaurantRepository, restaurantMapper, viaCepUseCase);
    }
    @Bean
    RestaurantUseCase restaurantUseCase(RestaurantGateway restaurantGateway){
        return new RestaurantUseCase(restaurantGateway);
    }

    //TODO: Colocar todos os UsesCases

}
