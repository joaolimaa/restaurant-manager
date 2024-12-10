package fiap.restaurant_manager.domain.entities;

import lombok.Data;

@Data
public class User {
    private String cpf;
    private String nome;
    private String email;

    public User(String cpf, String nome, String email) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new IllegalArgumentException("Cpf no padrão incorreto!");
        }

        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

}





