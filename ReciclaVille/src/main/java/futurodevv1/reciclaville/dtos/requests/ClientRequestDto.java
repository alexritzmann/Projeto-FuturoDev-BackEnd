package futurodevv1.reciclaville.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequestDto
{
    @NotBlank(message = "Nome do cliente é obrigatório")
    @Size(max = 100, message = "Nome do cliente deve ter até 100 caracteres")
    private String name;

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos")
    private String cnpj;

    @NotBlank(message = "Atividade econômica é obrigatória")
    private String economicActivity;

    @NotBlank(message = "Responsável é obrigatório")
    private String responsible;
}
