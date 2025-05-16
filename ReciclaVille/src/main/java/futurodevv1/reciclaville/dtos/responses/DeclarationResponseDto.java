package futurodevv1.reciclaville.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DeclarationResponseDto
{
    private Long id;
    private LocalDate dateDeclaration;
    private LocalDate dateStartPeriod;
    private LocalDate dateFinalPeriod;
    private BigDecimal totalMaterials;
    private BigDecimal totalCompensation;
    private ClientResponseDto client;
    private List<ItemDeclarationResponseDto> items;
}
