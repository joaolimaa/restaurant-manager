package fiap.restaurant_manager.infrastructure.settings;

import fiap.restaurant_manager.adapters.persistence.repository.UserRepository;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.UserRepositoryGateway;
import fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper.UserEntityMapper;
import fiap.restaurant_manager.application.gateways.UserGateway;
import fiap.restaurant_manager.application.usecases.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSetting {

    @Bean
    UserEntityMapper userEntityMapper(){
        return new UserEntityMapper();
    }

    @Bean
    UserRepositoryGateway userRepositoryGateway(UserRepository userRepository, UserEntityMapper mapper){
        return new UserRepositoryGateway(userRepository, mapper);
    }

    @Bean
    UserUseCase userUseCase(UserGateway userGateway){
        return new UserUseCase(userGateway);
    }

    //TODO: Colocar todos os UsesCases
}
