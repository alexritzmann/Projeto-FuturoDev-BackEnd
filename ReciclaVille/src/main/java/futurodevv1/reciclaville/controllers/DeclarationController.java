package futurodevv1.reciclaville.controllers;

import futurodevv1.reciclaville.dtos.requests.DeclarationRequestDto;
import futurodevv1.reciclaville.dtos.responses.DeclarationResponseDto;
import futurodevv1.reciclaville.services.DeclarationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/declarations")
public class DeclarationController
{
    private final DeclarationService declarationService;

    @GetMapping
    public List<DeclarationResponseDto> findAll()
    {
        return declarationService.findAll();
    }

    @GetMapping("/{id}")
    public DeclarationResponseDto findById(@PathVariable Long id)
    {
        return declarationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeclarationResponseDto create(@Valid @RequestBody DeclarationRequestDto declarationRequestDto)
    {
        return declarationService.create(declarationRequestDto);
    }

    @PutMapping("/{id}")
    public DeclarationResponseDto update(@PathVariable Long id, @RequestBody DeclarationRequestDto declarationRequestDto)
    {
        return declarationService.update(id, declarationRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        declarationService.delete(id);
    }
}
