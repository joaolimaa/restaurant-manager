package fiap.restaurant_manager.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private long idAddress;

    private String postalCode;

    private String city;

    private String state;

    private String neighborhood;

    private String street;

    private String number;

    public AddressEntity(String postalCode, String city, String state, String neighborhood, String street, String number) {
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
    }
}
