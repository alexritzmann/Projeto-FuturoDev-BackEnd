package futurodevv1.reciclaville.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDto
{
    private Long id;
    private String name;
    private String cnpj;
    private String economicActivity;
    private String responsible;
}