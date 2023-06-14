package rm349040.techchallenge1.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

}
