package futurodevv1.reciclaville.services;

import futurodevv1.reciclaville.dtos.responses.DashboardResponseDto;

import java.util.List;

public interface DashboardService
{
    List<DashboardResponseDto> getTotalCompensationByMaterial();
}
