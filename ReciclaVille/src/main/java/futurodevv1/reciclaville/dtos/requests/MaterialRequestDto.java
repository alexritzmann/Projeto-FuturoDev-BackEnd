package futurodevv1.reciclaville.dtos.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialRequestDto
{

    @NotBlank(message = "Nome do material é obrigatório")
    @Size(max = 50, message = "Nome do material pode conter até 50 caracteres")
    private String name;

    @NotNull(message = "Percentual de compensação é obrigatório")
    @DecimalMin(value = "0.01", message = "Permitido até 10 dígitos inteiros e 2 dígitos decimais")
    private BigDecimal percentageCompensation;
}
