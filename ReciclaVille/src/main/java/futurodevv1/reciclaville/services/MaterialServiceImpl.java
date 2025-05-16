package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.MaterialRequestDto;
import futurodevv1.reciclaville.dtos.responses.MaterialResponseDto;
import futurodevv1.reciclaville.entities.Material;
import futurodevv1.reciclaville.errors.exceptions.MaterialExistsException;
import futurodevv1.reciclaville.errors.exceptions.MaterialNotFoundException;
import futurodevv1.reciclaville.mappers.MaterialMapper;
import futurodevv1.reciclaville.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MaterialServiceImpl implements MaterialService
{
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MaterialResponseDto> findAll()
    {
        return materialRepository.findAll().stream()
                .map(materialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MaterialResponseDto findById(Long id)
    {
        return materialMapper.toDto(materialRepository.findById(id)
                .orElseThrow(() -> new MaterialNotFoundException(id)));
    }

    @Override
    @Transactional
    public MaterialResponseDto create(MaterialRequestDto dto)
    {
        if (materialRepository.existsByName(dto.getName()))
        {
            throw new MaterialExistsException("Já existe um material com este nome");
        }
        Material material = materialMapper.toEntity(dto);
        return materialMapper.toDto(materialRepository.save(material));
    }

    @Override
    @Transactional
    public MaterialResponseDto update(Long id, MaterialRequestDto dto)
    {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialNotFoundException(id));

        if (dto.getName() != null && !dto.getName().equals(material.getName()) && materialRepository.existsByName(dto.getName()))
        {
            throw new MaterialExistsException("Já existe um material com este nome");
        }

        materialMapper.updateEntity(dto, material);
        return materialMapper.toDto(materialRepository.save(material));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        if (!materialRepository.existsById(id))
        {
            throw new MaterialNotFoundException(id);
        }
        materialRepository.deleteById(id);
    }
}
