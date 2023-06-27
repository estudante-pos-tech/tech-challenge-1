package rm349040.techchallenge1.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {


        HttpStatus status = HttpStatus.NOT_FOUND;
        String type = "https://github.com/estudante-pos-tech/tech-challenge-1/entidade-nao-encontrada";
        String title = "Entidade não encontrada";
        String detail = ex.getMessage() + ". Tentando te ajudar ... passe um id que exista na base de dados que daí você terá o que solicita.";


        ApiError apiError = ApiError.builder()
                .status(status.value())
                .type(type)
                .title(title)
                .detail(detail)
                .timeStamp(Instant.now())
                .build();

        return handleExceptionInternal(ex, apiError , new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if(body == null){

            body = ApiError.builder().
                    title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase()).
                    status(statusCode.value()).
                    timeStamp(Instant.now()).
                    build();

        } else if (body instanceof String) {

            body = ApiError.builder().
                    title((String)body).
                    timeStamp(Instant.now()).
                    status(statusCode.value()).
                    build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

}
