package fiap.restaurant_manager.infrastructure.settings;

import fiap.restaurant_manager.adapters.persistence.repository.RestaurantRepository;
import fiap.restaurant_manager.application.gateways.RestaurantGateway;
import fiap.restaurant_manager.application.usecases.RestaurantUseCase;
import fiap.restaurant_manager.infrastructure.util.mappers.AddressMapper;
import fiap.restaurant_manager.infrastructure.util.mappers.OperatingHoursMapper;
import fiap.restaurant_manager.infrastructure.util.mappers.RestaurantControllerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantSetting {
    @Bean
    OperatingHoursMapper operatingHoursMapper(){
        return new OperatingHoursMapper();
    }
    @Bean
    AddressMapper addressMapper(){
        return new AddressMapper();
    }

    @Bean
    RestaurantControllerMapper restaurantEntityMapper(OperatingHoursMapper mapperOperatingHour, AddressMapper mapperAdress){
        return new RestaurantControllerMapper(mapperOperatingHour, mapperAdress);
    }

    @Bean
    RestaurantGateway restaurantGateway(RestaurantRepository restaurantRepository){
        return new RestaurantGateway(restaurantRepository);
    }
    @Bean
    RestaurantUseCase restaurantUseCase(RestaurantGateway restaurantGateway, RestaurantControllerMapper controllerMapper){
        return new RestaurantUseCase(restaurantGateway, controllerMapper);
    }
}
