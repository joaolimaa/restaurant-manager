package fiap.restaurant_manager.infrastructure.settings;

import fiap.restaurant_manager.adapters.persistence.repository.RestaurantRepository;
import fiap.restaurant_manager.adapters.persistence.mapper.RestaurantEntityMapper;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.application.usecases.RestaurantUseCase;
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
    RestaurantGateway restaurantGateway(RestaurantRepository restaurantRepository, RestaurantEntityMapper restaurantMapper){
        return new RestaurantGateway(restaurantRepository, restaurantMapper);
    }
    @Bean
    RestaurantUseCase restaurantUseCase(RestaurantGateway restaurantGateway){
        return new RestaurantUseCase(restaurantGateway);
    }

    //TODO: Colocar todos os UsesCases
}
