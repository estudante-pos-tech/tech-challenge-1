package rm349040.techchallenge1.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TechChallengeAppConfiguration {

    @Bean
    public Validator beanValidator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
