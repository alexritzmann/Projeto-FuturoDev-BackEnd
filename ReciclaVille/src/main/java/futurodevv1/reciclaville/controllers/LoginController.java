package futurodevv1.reciclaville.controllers;

import futurodevv1.reciclaville.dtos.requests.LoginRequestDto;
import futurodevv1.reciclaville.dtos.responses.LoginResponseDto;
import futurodevv1.reciclaville.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController
{
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto)
    {
        return ResponseEntity.ok(loginService.login(dto));

    }
}


