package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.LoginRequestDto;
import futurodevv1.reciclaville.dtos.responses.LoginResponseDto;

public interface LoginService
{
    LoginResponseDto login(LoginRequestDto dto);
}
