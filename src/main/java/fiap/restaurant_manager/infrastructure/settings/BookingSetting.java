package fiap.restaurant_manager.infrastructure.settings;



import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import fiap.restaurant_manager.adapters.persistence.mapper.BookingEntityMapper;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.application.usecases.BookingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingSetting {

    @Bean
    BookingEntityMapper bookingEntityMapper(){
        return new BookingEntityMapper();
    }

    @Bean
    BookingGateway bookingGateway(BookingRepository repository, BookingEntityMapper mapper){
        return new BookingGateway(repository, mapper);
    }

    @Bean
    BookingUseCase updateBookingUseCase(BookingGateway gateway) {
        return new BookingUseCase(gateway);
    }

    //TODO: Colocar todos os UsesCases
}
