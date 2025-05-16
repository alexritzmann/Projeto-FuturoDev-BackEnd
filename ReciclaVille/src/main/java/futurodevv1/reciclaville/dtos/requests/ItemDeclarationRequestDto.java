package futurodevv1.reciclaville.dtos.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDeclarationRequestDto
{
    @NotNull
    @Positive(message = "ID do material deve ser positivo")
    private Long materialId;

    @NotNull
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Permitido até 10 dígitos inteiros e 2 dígitos decimais")
    private BigDecimal tonsDeclared;
}
