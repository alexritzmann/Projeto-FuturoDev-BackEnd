package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.ClientRequestDto;
import futurodevv1.reciclaville.dtos.responses.ClientResponseDto;

import java.util.List;

public interface ClientService
{
    List<ClientResponseDto> findAll();
    ClientResponseDto findById(Long id);
    ClientResponseDto create(ClientRequestDto dto);
    ClientResponseDto update(Long id, ClientRequestDto dto);
    void delete(Long id);
}
