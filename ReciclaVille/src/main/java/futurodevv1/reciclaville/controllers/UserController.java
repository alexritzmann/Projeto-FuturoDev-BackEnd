package futurodevv1.reciclaville.controllers;

import futurodevv1.reciclaville.dtos.requests.UserRequestDto;
import futurodevv1.reciclaville.dtos.responses.UserResponseDto;
import futurodevv1.reciclaville.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> findAll()
    {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id)
    {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody UserRequestDto userRequestDto)
    {
        return userService.create(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto)
    {
        return userService.update(userRequestDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        userService.delete(id);
    }
}
