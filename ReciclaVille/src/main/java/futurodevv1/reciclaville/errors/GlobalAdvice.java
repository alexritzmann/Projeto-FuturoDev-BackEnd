package futurodevv1.reciclaville.errors;

import futurodevv1.reciclaville.dtos.responses.ErrorResponseDto;

import futurodevv1.reciclaville.errors.exceptions.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalAdvice
{
    @ExceptionHandler(InvalidPeriodException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidPeriod(InvalidPeriodException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponseDto(
                        "400",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(new ErrorResponseDto(
                "400",
                "Erro ao validar corpo da requisição",
                String.join(", ", errors),
                ex.getClass().getSimpleName()
        ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolation(ConstraintViolationException ex)
    {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .toList();

        return ResponseEntity.badRequest().body(new ErrorResponseDto(
                "400",
                "Erro de validação",
                errors.toString(),
                ex.getClass().getSimpleName()
        ));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFound(UsernameNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponseDto(
                        "401",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDenied(AccessDeniedException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ErrorResponseDto(
                        "403",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(AccessDeniedDeclarationException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedDeclaration(AccessDeniedDeclarationException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ErrorResponseDto(
                        "403",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> exception(NoSuchElementException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(
                "404",
                e.getLocalizedMessage(),
                e.getCause() != null ? e.getCause().getLocalizedMessage() : null,
                e.getClass().getSimpleName()
        ));
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> exception(ChangeSetPersister.NotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(
                "404",
                e.getLocalizedMessage(),
                e.getCause() != null ? e.getCause().getLocalizedMessage() : null,
                e.getClass().getSimpleName()
        ));
    }

    @ExceptionHandler(ClientExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleClientExists(ClientExistsException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDto(
                        "409",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(MaterialExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleMaterialExists(MaterialExistsException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDto(
                        "409",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameExists(UsernameExistsException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDto(
                        "409",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }
}
