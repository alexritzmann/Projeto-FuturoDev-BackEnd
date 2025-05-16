package futurodevv1.reciclaville.controllers;

import futurodevv1.reciclaville.dtos.responses.DashboardResponseDto;
import futurodevv1.reciclaville.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dashboard")
public class DashboardController
{
    private final DashboardService dashboardService;

    @GetMapping
    public List<DashboardResponseDto> getTotalCompensationByMaterial()
    {
        return dashboardService.getTotalCompensationByMaterial();
    }
}
