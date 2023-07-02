package rm349040.techchallenge1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TechChallenge1Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TechChallenge1Application.class, args);
    }


//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        //configurer.defaultContentType(MediaType.APPLICATION_XML);
//        WebMvcConfigurer.super.configureContentNegotiation(configurer);
//    }
}
