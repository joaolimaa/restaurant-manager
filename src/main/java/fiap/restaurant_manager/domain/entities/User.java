package fiap.restaurant_manager.domain.entities;

import lombok.Data;

@Data
public class User {
    private String cpf;
    private String name;
    private String email;

    public User(String cpf, String name, String email) {

        validateCpf(cpf);

        this.cpf = cpf;
        this.name = name;
        this.email = email;
    }

    private void validateCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new IllegalArgumentException("Cpf no padrão incorreto!");
        }
    }
}





