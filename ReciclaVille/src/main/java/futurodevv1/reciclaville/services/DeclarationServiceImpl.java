package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.configs.SecurityUtils;
import futurodevv1.reciclaville.dtos.requests.DeclarationRequestDto;
import futurodevv1.reciclaville.dtos.requests.ItemDeclarationRequestDto;
import futurodevv1.reciclaville.dtos.responses.DeclarationResponseDto;
import futurodevv1.reciclaville.entities.Client;
import futurodevv1.reciclaville.entities.Declaration;
import futurodevv1.reciclaville.entities.ItemDeclaration;
import futurodevv1.reciclaville.entities.Material;
import futurodevv1.reciclaville.errors.exceptions.*;
import futurodevv1.reciclaville.mappers.DeclarationMapper;
import futurodevv1.reciclaville.repositories.ClientRepository;
import futurodevv1.reciclaville.repositories.DeclarationRepository;
import futurodevv1.reciclaville.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeclarationServiceImpl implements DeclarationService
{
    private final DeclarationRepository declarationRepository;
    private final ClientRepository clientRepository;
    private final MaterialRepository materialRepository;
    private final DeclarationMapper declarationMapper;
    private final SecurityUtils securityUtils;

    @Override
    @Transactional(readOnly = true)
    public List<DeclarationResponseDto> findAll()
    {
        Long clientId = securityUtils.getClientIdIfUser();
        List<Declaration> declarations;

        if (clientId != null)
        {
            declarations = declarationRepository.findByClientId(clientId);
        } else
        {
            declarations = declarationRepository.findAll();
        }
        return declarations.stream().map(declarationMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DeclarationResponseDto findById(Long id)
    {
        Declaration declaration = declarationRepository.findById(id).orElseThrow(() -> new DeclarationNotFoundException(id));
        Long clientId = securityUtils.getClientIdIfUser();

        if (clientId != null && !declaration.getClient().getId().equals(clientId))
        {
            throw new AccessDeniedDeclarationException("Acesso negado a declaração de outro cliente");
        }
        return declarationMapper.toDto(declaration);
    }

    @Override
    @Transactional
    public DeclarationResponseDto create(DeclarationRequestDto dto)
    {
        validateDates(dto);

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(dto.getClientId()));

        Declaration declaration = declarationMapper.toEntity(dto, client);
        processItems(dto, declaration);
        calculateTotals(declaration);

        return declarationMapper.toDto(declarationRepository.save(declaration));
    }

    @Override
    @Transactional
    public DeclarationResponseDto update(Long id, DeclarationRequestDto dto)
    {
        Declaration declaration = declarationRepository.findById(id)
                .orElseThrow(() -> new DeclarationNotFoundException(id));

        validateDates(dto);
        validateClient(dto.getClientId());

        declaration.setDateStartPeriod(dto.getDateStartPeriod());
        declaration.setDateFinalPeriod(dto.getDateFinalPeriod());

        updateItems(dto, declaration);
        calculateTotals(declaration);

        return declarationMapper.toDto(declarationRepository.save(declaration));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        Declaration declaration = declarationRepository.findById(id)
                .orElseThrow(() -> new DeclarationNotFoundException(id));

        Long clientId = securityUtils.getClientIdIfUser();
        if (clientId != null && !declaration.getClient().getId().equals(clientId))
        {
            throw new AccessDeniedDeclarationException("Acesso negado a declaração de outro cliente");
        }
        declarationRepository.deleteById(id);
    }

    private void validateDates(DeclarationRequestDto dto)
    {
        if (dto.getDateStartPeriod().isAfter(dto.getDateFinalPeriod()))
        {
            throw new InvalidPeriodException("Data inicial deve ser anterior à data final");
        }
    }

    private void processItems(DeclarationRequestDto dto, Declaration declaration)
    {
        List<ItemDeclaration> items = dto.getItems().stream()
                .map(itemDto -> createItemFromDto(itemDto, declaration))
                .toList();
        declaration.setItems(items);
    }

    private void calculateItemCompensation(ItemDeclaration item)
    {
        BigDecimal compensation = item.getTonsDeclared()
                .multiply(item.getMaterial().getPercentageCompensation())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        item.setTonsCompensation(compensation);
    }

    private void calculateTotals(Declaration declaration)
    {
        BigDecimal materialsTotal = BigDecimal.ZERO;
        BigDecimal compensationTotal = BigDecimal.ZERO;

        for (ItemDeclaration item : declaration.getItems())
        {
            materialsTotal = materialsTotal.add(item.getTonsDeclared());
            compensationTotal = compensationTotal.add(item.getTonsCompensation());
        }

        declaration.setTotalMaterials(materialsTotal);
        declaration.setTotalCompensation(compensationTotal);
    }

    private void validateClient(Long clientId)
    {
        if (!clientRepository.existsById(clientId))
        {
            throw new ClientNotFoundException(clientId);
        }
    }

    private void updateItems(DeclarationRequestDto dto, Declaration declaration)
    {
        declaration.getItems().clear();
        dto.getItems().forEach(itemDto -> {
            ItemDeclaration item = createItemFromDto(itemDto, declaration);
            declaration.getItems().add(item);
        });
    }

    private ItemDeclaration createItemFromDto(ItemDeclarationRequestDto itemDto, Declaration declaration)
    {
        Material material = materialRepository.findById(itemDto.getMaterialId())
                .orElseThrow(() -> new MaterialNotFoundException(itemDto.getMaterialId()));

        ItemDeclaration item = new ItemDeclaration();
        item.setDeclaration(declaration);
        item.setMaterial(material);
        item.setTonsDeclared(itemDto.getTonsDeclared());
        item.setPercentageCompensation(material.getPercentageCompensation());

        calculateItemCompensation(item);
        return item;
    }
}
