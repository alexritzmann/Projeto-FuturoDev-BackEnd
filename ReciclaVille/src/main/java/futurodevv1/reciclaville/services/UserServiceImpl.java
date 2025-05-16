package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.UserRequestDto;
import futurodevv1.reciclaville.dtos.responses.UserResponseDto;
import futurodevv1.reciclaville.entities.User;
import futurodevv1.reciclaville.enums.UserRole;
import futurodevv1.reciclaville.errors.exceptions.UserNotFoundException;
import futurodevv1.reciclaville.errors.exceptions.UsernameExistsException;
import futurodevv1.reciclaville.mappers.UserMapper;
import futurodevv1.reciclaville.repositories.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll()
    {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto dto)
    {
        if (dto.getRole() == UserRole.USER && dto.getClient() == null)
        {
            throw new ValidationException("Cliente é obrigatório para usuários do tipo USER");
        }

        if (userRepository.existsByUsername(dto.getUsername()))
        {
            throw new UsernameExistsException("Username já em uso!");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(encoder.encode(dto.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponseDto update(UserRequestDto dto, Long id)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userMapper.updateEntity(dto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        if (!userRepository.existsById(id))
        {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
