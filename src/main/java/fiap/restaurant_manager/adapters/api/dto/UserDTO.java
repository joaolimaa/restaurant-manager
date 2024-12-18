package fiap.restaurant_manager.adapters.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.ReadOnlyProperty;

public record UserDTO(@JsonIgnore @ReadOnlyProperty Long id,
                      String name,
                      String email,
                      String cpf) {

    public UserDTO(String name, String email, String cpf) {
        this(null, name, email, cpf);
    }
}
