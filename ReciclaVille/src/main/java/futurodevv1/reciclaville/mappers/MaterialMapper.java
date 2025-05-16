package futurodevv1.reciclaville.mappers;

import futurodevv1.reciclaville.dtos.requests.MaterialRequestDto;
import futurodevv1.reciclaville.dtos.responses.MaterialResponseDto;
import futurodevv1.reciclaville.entities.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper
{
    public Material toEntity(MaterialRequestDto dto)
    {
        Material material = new Material();
        material.setName(dto.getName());
        material.setPercentageCompensation(dto.getPercentageCompensation());
        return material;
    }

    public MaterialResponseDto toDto(Material entity)
    {
        return MaterialResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .percentageCompensation(entity.getPercentageCompensation())
                .build();
    }

    public void updateEntity(MaterialRequestDto dto, Material entity)
    {
        if (dto.getName() != null)
        {
            entity.setName(dto.getName());
        }
        if (dto.getPercentageCompensation() != null)
        {
            entity.setPercentageCompensation(dto.getPercentageCompensation());
        }
    }
}
