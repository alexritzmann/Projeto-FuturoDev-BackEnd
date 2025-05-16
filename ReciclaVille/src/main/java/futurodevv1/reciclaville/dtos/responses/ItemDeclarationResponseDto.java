package futurodevv1.reciclaville.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemDeclarationResponseDto
{
    private Long id;
    private MaterialResponseDto material;
    private BigDecimal tonsDeclared;
    private BigDecimal tonsCompensation;
}
