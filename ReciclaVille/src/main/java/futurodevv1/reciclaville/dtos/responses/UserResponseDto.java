package futurodevv1.reciclaville.dtos.responses;

import futurodevv1.reciclaville.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto
{
    private Long id;
    private String name;
    private String username;
    private UserRole role;
    private ClientResponseDto client;
}
