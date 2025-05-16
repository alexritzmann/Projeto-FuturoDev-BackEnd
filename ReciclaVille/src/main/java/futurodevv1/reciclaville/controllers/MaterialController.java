package futurodevv1.reciclaville.controllers;

import futurodevv1.reciclaville.dtos.requests.MaterialRequestDto;
import futurodevv1.reciclaville.dtos.responses.MaterialResponseDto;
import futurodevv1.reciclaville.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/materials")
public class MaterialController
{
    private final MaterialService materialService;

    @GetMapping
    public List<MaterialResponseDto> findAll()
    {
        return materialService.findAll();
    }

    @GetMapping("/{id}")
    public MaterialResponseDto findById(@PathVariable Long id)
    {
        return materialService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponseDto create(@RequestBody MaterialRequestDto materialRequestDto)
    {
        return materialService.create(materialRequestDto);
    }

    @PutMapping("/{id}")
    public MaterialResponseDto update(@PathVariable Long id, @RequestBody MaterialRequestDto materialRequestDto)
    {
        return materialService.update(id, materialRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        materialService.delete(id);
    }
}
