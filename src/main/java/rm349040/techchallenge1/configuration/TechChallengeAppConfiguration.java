package rm349040.techchallenge1.configuration;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rm349040.techchallenge1.api.dtos.enderecos.DadosListagemEndereco;
import rm349040.techchallenge1.domain.model.Endereco;

@Configuration
public class TechChallengeAppConfiguration {

    @Bean
    public Validator beanValidator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.getConfiguration().
                setFieldMatchingEnabled(true).
                setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

//        modelMapper.
//                createTypeMap(Endereco.class, DadosListagemEndereco.class).
//                addMapping(Endereco::getNumero,DadosListagemEndereco::setNum);

        return modelMapper;
    }

}
