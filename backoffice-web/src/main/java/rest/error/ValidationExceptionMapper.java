package rest.error;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {


    @Override
    public Response toResponse(ConstraintViolationException ex) {
        List<FieldError> errors = ex.getConstraintViolations().stream()
                .map(v -> new FieldError(
                        v.getPropertyPath().toString(),
                        v.getMessage()
                ))
                .toList();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ValidationError("VALIDATION_ERROR", errors))
                .build();
    }

    public record ValidationError(String code, List<FieldError> errors) {}
    public record FieldError(String field, String message) {}
}
