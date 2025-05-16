package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.UserRequestDto;
import futurodevv1.reciclaville.dtos.responses.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{
    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto create(UserRequestDto dto);
    UserResponseDto update(UserRequestDto dto, Long id);
    void delete(Long id);
}
