package fiap.restaurant_manager.adapters.persistence.entities;

import fiap.restaurant_manager.domain.enums.KitchenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private AddressEntity address;

    private KitchenType kitchenType;

    private String cnpj;

    @OneToMany
    @JoinColumn(name = "id_operating_hours", nullable = false)
    private List<OperatingHoursEntity> operatingHours;

    private int capacity;

    public RestaurantEntity(String name, AddressEntity address, KitchenType kitchenType, String cnpj, List<OperatingHoursEntity> operatingHours, int capacity) {
        this.name = name;
        this.address = address;
        this.kitchenType = kitchenType;
        this.cnpj = cnpj;
        this.operatingHours = operatingHours;
        this.capacity = capacity;
    }
}
