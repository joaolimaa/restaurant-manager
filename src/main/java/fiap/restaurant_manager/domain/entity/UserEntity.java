package fiap.restaurant_manager.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @NotBlank(message = "O nome do usuário não pode ser vazio")
    @Schema(description = "Nome do usuário", example = "Fulano", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "O e-mail não pode ser vazio")
    @Schema(description = "Email do usuário", example = "teste@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @CPF(message = "Formato do CPF inválido")
    @NotBlank(message = "O CPF não pode ser vazio")
    @Schema(description = "CPF do usuário", example = "061.195.720-58", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;
}
