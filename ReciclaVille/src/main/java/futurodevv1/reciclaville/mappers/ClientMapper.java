package futurodevv1.reciclaville.mappers;

import futurodevv1.reciclaville.dtos.requests.ClientRequestDto;
import futurodevv1.reciclaville.dtos.responses.ClientResponseDto;
import futurodevv1.reciclaville.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper
{
    public Client toEntity(ClientRequestDto dto)
    {
        Client client = new Client();
        client.setName(dto.getName());
        client.setCnpj(dto.getCnpj());
        client.setEconomicActivity(dto.getEconomicActivity());
        client.setResponsible(dto.getResponsible());
        return client;
    }

    public ClientResponseDto toDto(Client entity)
    {
        return ClientResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cnpj(entity.getCnpj())
                .economicActivity(entity.getEconomicActivity())
                .responsible(entity.getResponsible())
                .build();
    }

    public void updateEntity(ClientRequestDto dto, Client entity)
    {
        if (dto.getName() != null)
        {
            entity.setName(dto.getName());
        }
        if (dto.getCnpj() != null)
        {
            entity.setCnpj(dto.getCnpj());
        }
        if (dto.getEconomicActivity() != null)
        {
            entity.setEconomicActivity(dto.getEconomicActivity());
        }
        if (dto.getResponsible() != null)
        {
            entity.setResponsible(dto.getResponsible());
        }
    }
}