package futurodevv1.reciclaville.mappers;

import futurodevv1.reciclaville.dtos.requests.UserRequestDto;
import futurodevv1.reciclaville.dtos.responses.UserResponseDto;
import futurodevv1.reciclaville.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper
{
    private final ClientMapper clientMapper;

    public User toEntity(UserRequestDto dto)
    {
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setClient(dto.getClient());
        return user;
    }

    public UserResponseDto toDto(User entity)
    {
        return toDtoWithClientDto(entity);
    }

    public UserResponseDto toDtoWithClientDto(User entity)
    {
        return UserResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .role(entity.getRole())
                .client(entity.getClient() != null ? clientMapper.toDto(entity.getClient()) : null)
                .build();
    }

    public List<UserResponseDto> toDtos(List<User> users)
    {
        return users.stream().map(this::toDtoWithClientDto).collect(Collectors.toList());
    }

    public void updateEntity(UserRequestDto dto, User entity)
    {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getUsername() != null) {
            entity.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            entity.setPassword(dto.getPassword());
        }
        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());
        }
        if (dto.getClient() != null) {
            entity.setClient(dto.getClient());
        }
    }
}