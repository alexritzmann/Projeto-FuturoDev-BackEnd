package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.MaterialRequestDto;
import futurodevv1.reciclaville.dtos.responses.MaterialResponseDto;

import java.util.List;

public interface MaterialService
{
    List<MaterialResponseDto> findAll();
    MaterialResponseDto findById(Long id);
    MaterialResponseDto create(MaterialRequestDto dto);
    MaterialResponseDto update(Long id, MaterialRequestDto dto);
    void delete(Long id);
}
