package fiap.restaurant_manager.domain.entities;

import fiap.restaurant_manager.domain.enums.KitchenType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Restaurant {
    private String name;
    private Address address;
    private KitchenType kitchenType;
    private String cnpj;
    private List<OperatingHours> operatingHours;
    private int capacity;
}
