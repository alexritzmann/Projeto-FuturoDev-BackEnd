package futurodevv1.reciclaville.dtos.requests;

import futurodevv1.reciclaville.entities.Client;
import futurodevv1.reciclaville.enums.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDto
{
    @NotBlank(message = "Nome de usuário é obrigatório")
    @Size(min = 3, max = 100, message = "Nome de usuário deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "Username é obrigatório")
    @Size(min = 4, max = 20, message = "Username deve ter entre 4 e 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$",
            message = "Username contém caracteres inválidos")
    private String username;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Senha deve conter pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial")
    private String password;

    @NotNull(message = "Perfil é obrigatório. Escolha entre ADMIN ou USER")
    private UserRole role;

    @Valid
    private Client client;
}
