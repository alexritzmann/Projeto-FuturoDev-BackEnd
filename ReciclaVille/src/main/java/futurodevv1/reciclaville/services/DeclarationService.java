package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.requests.DeclarationRequestDto;
import futurodevv1.reciclaville.dtos.responses.DeclarationResponseDto;

import java.util.List;

public interface DeclarationService
{
    List<DeclarationResponseDto> findAll();
    DeclarationResponseDto findById(Long id);
    DeclarationResponseDto create(DeclarationRequestDto dto);
    DeclarationResponseDto update(Long id, DeclarationRequestDto dto);
    void delete(Long id);

}
