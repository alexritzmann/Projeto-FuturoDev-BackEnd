package futurodevv1.reciclaville.controllers;

import futurodevv1.reciclaville.dtos.requests.ClientRequestDto;
import futurodevv1.reciclaville.dtos.responses.ClientResponseDto;
import futurodevv1.reciclaville.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController
{
    private final ClientService clientService;

    @GetMapping
    public List<ClientResponseDto> findAll()
    {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientResponseDto findById(@PathVariable Long id)
    {
        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDto create(@RequestBody ClientRequestDto clientRequestDto)
    {
        return clientService.create(clientRequestDto);
    }

    @PutMapping("/{id}")
    public ClientResponseDto update(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto)
    {
        return clientService.update(id, clientRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        clientService.delete(id);
    }
}