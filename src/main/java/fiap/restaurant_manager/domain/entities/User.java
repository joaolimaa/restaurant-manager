package fiap.restaurant_manager.domain.entities;

import lombok.Data;

import static fiap.restaurant_manager.infrastructure.util.formatters.formatCPF;

@Data
public class User {
    private String name;
    private String email;
    private String cpf;

    public User(String name, String email, String cpf) {
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.cpf = validateCpf(cpf);
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório e não pode estar vazio.");
        }
        return name.trim();
    }

    private String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail é obrigatório e não pode estar vazio.");
        }
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("E-mail no padrão incorreto!");
        }
        return email.trim();
    }

    private String validateCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório e não pode estar vazio.");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF no padrão incorreto! Deve conter exatamente 11 dígitos numéricos.");
        }
        return formatCPF(cpf);
    }
}
