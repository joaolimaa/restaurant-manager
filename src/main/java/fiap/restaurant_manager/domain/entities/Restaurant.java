package fiap.restaurant_manager.domain.entities;

import fiap.restaurant_manager.domain.enums.KitchenType;
import lombok.Data;

import java.time.LocalTime;

import static fiap.restaurant_manager.infrastructure.util.formatters.formatCNPJ;
import static fiap.restaurant_manager.infrastructure.util.formatters.formatPostalCode;

@Data
public class Restaurant {
    private String name;
    private String postalCode;
    private String street;
    private String number;
    private KitchenType kitchenType;
    private String cnpj;
    private int capacity;
    private LocalTime initialTime;
    private LocalTime finalTime;

    public Restaurant(String name, String postalCode, String street, String number, KitchenType kitchenType,
                      String cnpj, int capacity, LocalTime initialTime, LocalTime finalTime) {
        this.name = validateField(name, "Nome");
        this.postalCode = validatePostalCode(postalCode);
        this.street = validateField(street, "Rua");
        this.number = validateField(number, "Número do endereço");
        this.kitchenType = kitchenType;
        this.cnpj = validateCNPJ(cnpj);
        this.capacity = validateCapacity(capacity);
        this.initialTime = validateField(initialTime, "Horário de início do atendimento");
        this.finalTime = validateField(finalTime, "Horário de término do atendimento");
        validateTimeOrder(initialTime, finalTime);
    }

    private String validatePostalCode(String postalCode) {
        if (postalCode == null || !postalCode.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP no padrão incorreto! Deve conter exatamente 8 dígitos numéricos.");
        }
        return formatPostalCode(postalCode);
    }

    private String validateCNPJ(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ no padrão incorreto! Deve conter exatamente 14 dígitos numéricos.");
        }
        return formatCNPJ(cnpj);
    }

    private <T> T validateField(T field, String fieldName) {
        if (field == null) {
            throw new IllegalArgumentException(fieldName + " é obrigatório e não pode ser nulo.");
        }
        return field;
    }

    private Integer validateCapacity(Integer capacity) {
        if (capacity == null || capacity <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser enviada e maior que zero.");
        }
        return capacity;
    }

    private void validateTimeOrder(LocalTime initialTime, LocalTime finalTime) {
        if (initialTime.isAfter(finalTime)) {
            throw new IllegalArgumentException("Horário de início deve ser anterior ao horário de término.");
        }
    }
}
