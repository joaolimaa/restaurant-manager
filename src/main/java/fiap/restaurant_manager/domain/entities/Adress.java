package fiap.restaurant_manager.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Adress {
    private String postalCode;
    private String city;
    private String state;
    private String neighborhood;
    private String street;
    private String number;
}
