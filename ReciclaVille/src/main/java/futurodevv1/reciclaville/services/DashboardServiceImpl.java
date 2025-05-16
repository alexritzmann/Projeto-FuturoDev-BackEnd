package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.configs.SecurityUtils;
import futurodevv1.reciclaville.dtos.responses.DashboardResponseDto;
import futurodevv1.reciclaville.repositories.DeclarationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements DashboardService
{
    private final DeclarationRepository declarationRepository;
    private final SecurityUtils securityUtils;

    @Override
    public List<DashboardResponseDto> getTotalCompensationByMaterial()
    {
        Long clientId = securityUtils.getClientIdIfUser();

        List<Map<String, Object>> results = clientId != null ?
                declarationRepository.findTotalCompensationByMaterialAndClient(clientId) :
                declarationRepository.findTotalCompensationByMaterial();

        return results.stream()
                .map(this::mapToDashboardDto)
                .collect(Collectors.toList());
    }

    private DashboardResponseDto mapToDashboardDto(Map<String, Object> entry)
    {
        return DashboardResponseDto.builder()
                .material((String) entry.get("material"))
                .totalCompensation((BigDecimal) entry.get("total"))
                .build();
    }
}
