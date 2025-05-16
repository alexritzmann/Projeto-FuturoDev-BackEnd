package futurodevv1.reciclaville.mappers;

import futurodevv1.reciclaville.dtos.requests.DeclarationRequestDto;
import futurodevv1.reciclaville.dtos.responses.DeclarationResponseDto;
import futurodevv1.reciclaville.dtos.responses.ItemDeclarationResponseDto;
import futurodevv1.reciclaville.entities.Client;
import futurodevv1.reciclaville.entities.Declaration;
import futurodevv1.reciclaville.entities.ItemDeclaration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeclarationMapper
{
    private final ClientMapper clientMapper;
    private final MaterialMapper materialMapper;

    public Declaration toEntity(DeclarationRequestDto dto, Client client)
    {
        Declaration declaration = new Declaration();
        declaration.setDateDeclaration(dto.getDateDeclaration());
        declaration.setDateStartPeriod(dto.getDateStartPeriod());
        declaration.setDateFinalPeriod(dto.getDateFinalPeriod());
        declaration.setClient(client);
        return declaration;
    }

    public DeclarationResponseDto toDto(Declaration entity)
    {
        return DeclarationResponseDto.builder()
                .id(entity.getId())
                .dateDeclaration(entity.getDateDeclaration())
                .dateStartPeriod(entity.getDateStartPeriod())
                .dateFinalPeriod(entity.getDateFinalPeriod())
                .totalMaterials(entity.getTotalMaterials())
                .totalCompensation(entity.getTotalCompensation())
                .client(clientMapper.toDto(entity.getClient()))
                .items(mapItems(entity.getItems()))
                .build();
    }

    private List<ItemDeclarationResponseDto> mapItems(List<ItemDeclaration> items)
    {
        return items.stream().map(item -> ItemDeclarationResponseDto.builder()
                .id(item.getId())
                .material(materialMapper.toDto(item.getMaterial()))
                .tonsDeclared(item.getTonsDeclared())
                .tonsCompensation(item.getTonsCompensation())
                .build()).toList();
    }
}
