package fiap.restaurant_manager.infrastructure.settings;



import fiap.restaurant_manager.adapters.persistence.repository.BookingRepository;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.BookingRepositoryGateway;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper.BookingEntityMapper;
import fiap.restaurant_manager.application.gateways.BookingGateway;
import fiap.restaurant_manager.application.usecases.BookingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingSetting {

    @Bean
    BookingEntityMapper reservaEntityMapper(){
        return new BookingEntityMapper();
    }

    @Bean
    BookingRepositoryGateway reservaRepositoryGateway(BookingRepository reservaRepository, BookingEntityMapper mapper){
        return new BookingRepositoryGateway(reservaRepository, mapper);
    }

    @Bean
    BookingUseCase atualizarReservaUseCase(BookingGateway reservaGateway) {
        return new BookingUseCase(reservaGateway);
    }

    //TODO: Colocar todos os UsesCases
}
