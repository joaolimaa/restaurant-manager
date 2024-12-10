package fiap.restaurant_manager.domain.entities;

import fiap.restaurant_manager.adapters.persistence.entities.AddressEntity;
import fiap.restaurant_manager.adapters.persistence.entities.OperatingHoursEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Restaurant {

    private String name;
    private AddressEntity addressEntity;
    private String kitchenType;
    private String cnpj;
    private List<OperatingHoursEntity> operatingHourEntities;
    private int capacity;
}
