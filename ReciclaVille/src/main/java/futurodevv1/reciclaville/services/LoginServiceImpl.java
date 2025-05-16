package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.configs.JwtConfig;
import futurodevv1.reciclaville.dtos.requests.LoginRequestDto;
import futurodevv1.reciclaville.dtos.responses.LoginResponseDto;
import futurodevv1.reciclaville.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService
{
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;

    public LoginResponseDto login(LoginRequestDto dto)
    {
        try
        {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            User user = (User) auth.getPrincipal();
            String token = jwtConfig.generateToken(user);

            return LoginResponseDto.builder()
                    .token(token)
                    .username(user.getUsername())
                    .role(user.getRole().name())
                    .clientName(user.getClient() != null ? user.getClient().getName() : null)
                    .build();

        } catch (BadCredentialsException e)
        {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }
}
