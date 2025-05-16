package futurodevv1.reciclaville.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DeclarationRequestDto
{
    @NotNull(message = "ID do cliente é obrigatório")
    @Positive(message = "ID do cliente deve ser positivo")
    private Long clientId;

    @PastOrPresent(message = "Data da declaração deve ser igual ou menor que atual")
    private LocalDate dateDeclaration = LocalDate.now();

    @NotNull(message = "Data inicial é obrigatória")
    private LocalDate dateStartPeriod;

    @NotNull(message = "Data final é obrigatória")
    private LocalDate dateFinalPeriod;

    @Valid
    @NotEmpty(message = "Você deve informar pelo menos um item")
    private List<ItemDeclarationRequestDto> items;
}
