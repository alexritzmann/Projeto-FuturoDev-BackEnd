package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.ClientRequestDto;
import futurodevv1.reciclaville.dtos.responses.ClientResponseDto;
import futurodevv1.reciclaville.entities.Client;
import futurodevv1.reciclaville.errors.exceptions.ClientExistsException;
import futurodevv1.reciclaville.errors.exceptions.ClientNotFoundException;
import futurodevv1.reciclaville.mappers.ClientMapper;
import futurodevv1.reciclaville.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService
{
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDto> findAll()
    {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDto findById(Long id)
    {
        return clientMapper.toDto(clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id)));
    }

    @Override
    @Transactional
    public ClientResponseDto create(ClientRequestDto dto)
    {
        if (clientRepository.existsByCnpj(dto.getCnpj()))
        {
            throw new ClientExistsException("Já existe um cliente com este CNPJ");
        }
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    @Transactional
    public ClientResponseDto update(Long id, ClientRequestDto dto)
    {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        clientMapper.updateEntity(dto, client);
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        if (!clientRepository.existsById(id))
        {
            throw new ClientNotFoundException(id);
        }
        clientRepository.deleteById(id);
    }
}