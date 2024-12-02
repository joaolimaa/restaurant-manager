package fiap.restaurant_manager.config;


import fiap.restaurant_manager.application.gateways.reserva.ReservaGateway;
import fiap.restaurant_manager.application.usecases.reserva.AtualizarReservaUseCase;
import fiap.restaurant_manager.application.usecases.reserva.ListarTodasReservasUseCase;
import fiap.restaurant_manager.infra.gateways.reserva.ReservaEntityMapper;
import fiap.restaurant_manager.infra.gateways.reserva.ReservaRepositoryGateway;
import fiap.restaurant_manager.infra.persistence.reserva.ReservaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaConfig {

    @Bean
    ReservaEntityMapper reservaEntityMapper(){
        return new ReservaEntityMapper();
    }

    @Bean
    ReservaRepositoryGateway reservaRepositoryGateway(ReservaRepository reservaRepository, ReservaEntityMapper mapper){
        return new  ReservaRepositoryGateway(reservaRepository, mapper);
    }

    @Bean
    AtualizarReservaUseCase atualizarReservaUseCase(ReservaGateway reservaGateway) {
        return new AtualizarReservaUseCase(reservaGateway);
    }

    @Bean
    ListarTodasReservasUseCase atualizaReserva(ReservaGateway reservaGateway) {
        return new ListarTodasReservasUseCase(reservaGateway);
    }
}
