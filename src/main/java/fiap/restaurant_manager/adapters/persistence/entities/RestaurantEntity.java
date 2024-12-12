package fiap.restaurant_manager.adapters.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fiap.restaurant_manager.domain.enums.KitchenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Restaurant")
public class RestaurantEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private AddressEntity address;

    private KitchenType kitchenType;

    private String cnpj;

    @OneToMany
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
