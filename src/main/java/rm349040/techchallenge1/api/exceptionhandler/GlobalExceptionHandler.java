package rm349040.techchallenge1.api.exceptionhandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import rm349040.techchallenge1.api.exceptionhandler.ApiError;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;
import rm349040.techchallenge1.util.Messages;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException e){

        ApiError notFoundError = ApiError.builder().
                message(e.getMessage()).
                timeStamp(Instant.now()).
                build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundError);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){

        ApiError httpMediaTypeNotSupportedError = ApiError.builder().
                message("Tipo de mídia não é aceito").
                timeStamp(Instant.now()).
                build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(httpMediaTypeNotSupportedError);

    }

}
