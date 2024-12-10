package fiap.restaurant_manager.adapters.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CNPJ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Entity
@Table(name = "restaurant")
@Data
public class RestaurantEntity {

    @Id
    @Schema(description = "ID do restaurante", example = "1")
    private Long id;
    private String name;
    private AddressEntity addressEntity;
    private String kitchenType;
    private String cnpj;
    private List<OperatingHoursEntity> operatingHourEntities;
    private int capacity;

    public RestaurantEntity(String name, AddressEntity addressEntity, String kitchenType, String cnpj, List<OperatingHoursEntity> operatingHourEntities, int capacity) {
        this.name = name;
        this.addressEntity = addressEntity;
        this.kitchenType = kitchenType;
        this.cnpj = cnpj;
        this.operatingHourEntities = operatingHourEntities;
        this.capacity = capacity;
    }
}
