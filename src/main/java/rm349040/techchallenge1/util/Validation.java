package rm349040.techchallenge1.util;

import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rm349040.techchallenge1.domain.exceptions.ApiValidationException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Validation {

    static int instancesNumber = 0;

    public Validation() {
        System.out.println("THERE IS(ARE) " + ++instancesNumber + " rm349040.techchallenge1.util.Validation INSTANCE(S)");
    }

    @Autowired
    private jakarta.validation.Validator validator;

    synchronized public <T> void throwExceptionIfDataIsWrong(T obj) throws ApiValidationException {

        List<String> violacoes = getViolacoes(obj);

        if( ! violacoes.isEmpty() ){
             throw new ApiValidationException("",violacoes.toString(),null);
         }

    }

    synchronized public <T> String errorMessage(T obj) {

        StringBuilder sb = new StringBuilder();

        List<String> violacoes = getViolacoes(obj);

        return violacoes.toString();

    }


    synchronized private <T> List<String> getViolacoes(T obj) {

        Set<ConstraintViolation<T>> violacoes = validator.validate(obj);

        List<String> violacoesToList = violacoes.stream().
                map((violacao) -> new String(violacao.getPropertyPath() + ":" + violacao.getMessage()))
                .collect(Collectors.toList());

        return violacoesToList;

    }


}
