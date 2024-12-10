package fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper;

import fiap.restaurant_manager.adapters.persistence.entities.UserEntity;

import fiap.restaurant_manager.domain.entities.User;

public class UserEntityMapper {


    public UserEntity toEntity(User userDomain) {
        return new UserEntity(
                userDomain.getNome(),
                userDomain.getEmail(),
                userDomain.getCpf());
    }

    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getCpf());
    }
}
