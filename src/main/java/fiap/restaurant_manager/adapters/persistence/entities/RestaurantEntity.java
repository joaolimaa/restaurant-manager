package fiap.restaurant_manager.adapters.persistence.entities;

import fiap.restaurant_manager.domain.enums.KitchenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String postalCode;
    private String street;
    private String number;
    private KitchenType kitchenType;
    private String cnpj;
    private int capacity;
    private LocalTime initialTime;
    private LocalTime finalTime;

    public RestaurantEntity(String name, String postalCode, String street, String number, KitchenType kitchenType,
                            String cnpj, int capacity, LocalTime initialTime, LocalTime finalTime) {
        this.name = name;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.kitchenType = kitchenType;
        this.cnpj = cnpj;
        this.capacity = capacity;
    }
}
