package fiap.restaurant_manager.adapters.api.dto;

import org.springframework.data.annotation.ReadOnlyProperty;

public record UserDTO(@ReadOnlyProperty Long id,
                      String name,
                      String email,
                      String cpf) {

    public UserDTO(String name, String email, String cpf) {
        this(null, name, email, cpf);
    }
}
