package fiap.restaurant_manager.domain.entities;

import lombok.Data;

@Data
public class User {
    private String name;
    private String email;
    private String cpf;

    public User(String name, String email, String cpf) {

        validateCpf(cpf);

        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    private void validateCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new IllegalArgumentException("Cpf no padrão incorreto!");
        }
    }
}





