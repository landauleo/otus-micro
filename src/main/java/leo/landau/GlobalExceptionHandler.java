package leo.landau;

import javax.persistence.EntityNotFoundException;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.security.authentication.AuthorizationException;
import jakarta.inject.Singleton;

@Produces //может производить (или "выдавать") HTTP-ответы или другие типы ответов, которые будут переданы клиенту
@Singleton
public class GlobalExceptionHandler implements ExceptionHandler<Throwable, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, Throwable exception) {
        exception.printStackTrace();

        if (exception instanceof EntityNotFoundException) {
            return HttpResponse.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Entity not found", exception.getMessage()));
        } else if (exception instanceof IllegalArgumentException) {
            return HttpResponse.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid argument", exception.getMessage()));
        } else if (exception instanceof AuthorizationException) {
            return HttpResponse.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("Action is forbidden", exception.getMessage()));
        } else {
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An unexpected error occurred"));
        }
    }

    public static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

    }

}

