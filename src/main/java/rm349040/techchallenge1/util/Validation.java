package rm349040.techchallenge1.util;

import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Validation {

    static int instancesNumber=0;

    public Validation() {
        System.out.println("THERE IS(ARE) "+ ++instancesNumber +" rm349040.techchallenge1.util.Validation INSTANCE(S)");
    }

    @Autowired
    private jakarta.validation.Validator validator;

    synchronized public <T> boolean isDadosOk(T obj) {
        return getViolacoes(obj).isEmpty();
    }

     synchronized public <T> String errorMessage(T obj) {

        StringBuilder sb = new StringBuilder();

        List<String> violacoes = getViolacoes(obj);

        violacoes.stream().forEach(violacao -> {
            sb.append(violacao);
            sb.append("\n");
        });


        return sb.toString();

    }


     synchronized private <T> List<String> getViolacoes(T obj) {

        Set<ConstraintViolation<T>> violacoes = validator.validate(obj);

        List<String> violacoesToList = violacoes.stream().
                map((violacao) -> new String(violacao.getPropertyPath() + ":" + violacao.getMessage()))
                .collect(Collectors.toList());

        return violacoesToList;

    }



}
