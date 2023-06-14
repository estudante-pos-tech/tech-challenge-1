package rm349040.techchallenge1.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import rm349040.techchallenge1.util.Messages;

@Configuration
@ControllerAdvice
public class AppConfiguration {

    @Bean
    public Validator beanValidator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public @ResponseBody ResponseEntity handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        return ResponseEntity.badRequest().body(Messages.ERRO_METODO_REQUEST_NAO_SUPORTADO());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public @ResponseBody ResponseEntity handleHttpMessageNotReadableException(HttpServletRequest request, Exception ex){
        return  ResponseEntity.badRequest().body(Messages.ERRO_MENSAGEM_NAO_LEGIVEL(request,ex));
    }


}
