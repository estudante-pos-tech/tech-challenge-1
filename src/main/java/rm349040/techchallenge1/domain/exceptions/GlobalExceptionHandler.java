package rm349040.techchallenge1.domain.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import rm349040.techchallenge1.util.Messages;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ApiError apiError = new ApiError();

    @ExceptionHandler(Exception.class)
    public synchronized @ResponseBody ResponseEntity<ApiError> handleException(HttpServletRequest request, Exception ex){

        try{

            ApiException apiException = (ApiException) ex;
            apiError.setTimeStamp(Instant.now());
            apiError.setError(apiException.getError());
            apiError.setMessage(apiException.getMessage());
            apiError.setStatus(apiException.getStatus());
            apiError.setPath(request.getRequestURL().toString());

            return ResponseEntity.status(apiError.getStatus()).body(apiError);


        }catch (Exception e){
            //não faz nada
        }


        apiError.setTimeStamp(Instant.now());
        apiError.setError(Messages.ERRO_GLOBAL(request,ex));
        apiError.setMessage(Messages.ERRO_GLOBAL(request,ex));
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setPath(request.getRequestURL().toString());

        return ResponseEntity.badRequest().body(apiError);
    }

}
