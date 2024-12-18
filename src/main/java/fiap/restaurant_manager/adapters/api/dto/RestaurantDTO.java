package fiap.restaurant_manager.adapters.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fiap.restaurant_manager.domain.enums.KitchenType;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalTime;

public record RestaurantDTO(@JsonIgnore @ReadOnlyProperty Long id,
                            String name,
                            String postalCode,
                            String street,
                            String number,
                            KitchenType kitchenType,
                            String cnpj,
                            int capacity,
                            LocalTime initialTime,
                            LocalTime finalTime) {

    public RestaurantDTO(String name, String postalCode, String street, String number,
                         KitchenType kitchenType, String cnpj, int capacity,
                         LocalTime initialTime, LocalTime finalTime) {
        this(null, name, postalCode, street, number, kitchenType, cnpj, capacity, initialTime, finalTime);
    }
}
