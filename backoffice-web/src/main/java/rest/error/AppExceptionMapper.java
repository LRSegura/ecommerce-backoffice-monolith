package rest.error;

import com.code2ever.backoffice.application.common.exception.ConflictException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException ex) {
        if (ex instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiError.of("NOT_FOUND", ex.getMessage()))
                    .build();
        }
        if (ex instanceof ConflictException) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(ApiError.of("CONFLICT", ex.getMessage()))
                    .build();
        }

        throw ex;

//        return Response.serverError()
//                .entity(ApiError.of("INTERNAL_ERROR", "Unexpected error"))
//                .build();
    }

    public record ApiError(String code, String message) {
        public static ApiError of(String code, String message) {
            return new ApiError(code, message);
        }
    }
}
