package futurodevv1.reciclaville.dtos.responses;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponseDto(
        String code,
        String message,
        String cause,
        String exceptionClassName,
        List<String> errors
)
{
    public ErrorResponseDto(String code, String message, String cause, String exceptionClassName)
    {
        this(code, message, cause, exceptionClassName, null);
    }
}