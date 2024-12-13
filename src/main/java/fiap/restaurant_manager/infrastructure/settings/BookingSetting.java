package fiap.restaurant_manager.infrastructure.settings;



import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.application.usecases.BookingUseCase;
import fiap.restaurant_manager.infrastructure.util.mappers.BookingControllerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingSetting {

    @Bean
    BookingControllerMapper bookingEntityMapper(){
        return new BookingControllerMapper();
    }

    @Bean
    BookingGateway bookingGateway(BookingRepository repository){
        return new BookingGateway(repository);
    }

    @Bean
    BookingUseCase bookingUseCase(BookingGateway gateway, BookingControllerMapper controllerMapper) {
        return new BookingUseCase(gateway, controllerMapper);
    }
}
