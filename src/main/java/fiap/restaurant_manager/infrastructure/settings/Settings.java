package fiap.restaurant_manager.infrastructure.settings;

import fiap.restaurant_manager.adapters.persistence.repository.*;
import fiap.restaurant_manager.application.gateways.*;
import fiap.restaurant_manager.application.usecases.BookingUseCase;
import fiap.restaurant_manager.application.usecases.RestaurantUseCase;
import fiap.restaurant_manager.application.usecases.UserUseCase;
import fiap.restaurant_manager.infrastructure.util.mappers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Settings {

    @Bean
    RestaurantControllerMapper restaurantEntityMapper(){
        return new RestaurantControllerMapper();
    }

    @Bean
    RestaurantGateway restaurantGateway(RestaurantRepository restaurantRepository){
        return new RestaurantGateway(restaurantRepository);
    }


    @Bean
    RestaurantUseCase restaurantUseCase(
        RestaurantGateway restaurantGateway,
        RestaurantControllerMapper restaurantControllerMapper
    ){
        return new RestaurantUseCase(
                restaurantGateway,
                restaurantControllerMapper
        );
    }

    @Bean
    UserControllerMapper userEntityMapper(){
        return new UserControllerMapper();
    }

    @Bean
    UserGateway userRepositoryGateway(UserRepository userRepository){
        return new UserGateway(userRepository);
    }

    @Bean
    UserUseCase userUseCase(UserGateway userGateway, UserControllerMapper mapper){
        return new UserUseCase(userGateway, mapper);
    }

    @Bean
    BookingControllerMapper bookingEntityMapper(){
        return new BookingControllerMapper();
    }

    @Bean
    BookingGateway bookingGateway(BookingRepository repository){
        return new BookingGateway(repository);
    }

    @Bean
    BookingUseCase bookingUseCase(BookingGateway gateway, BookingControllerMapper controllerMapper, RestaurantUseCase restaurantUseCase) {
        return new BookingUseCase(gateway, controllerMapper, restaurantUseCase);
    }
}
