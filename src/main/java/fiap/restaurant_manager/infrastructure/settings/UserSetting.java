package fiap.restaurant_manager.infrastructure.settings;

import fiap.restaurant_manager.adapters.persistence.repository.UserRepository;
import fiap.restaurant_manager.application.gateways.UserGateway;
import fiap.restaurant_manager.application.usecases.UserUseCase;
import fiap.restaurant_manager.infrastructure.util.mappers.UserControllerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSetting {

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

}
