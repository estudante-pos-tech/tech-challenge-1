package rm349040.techchallenge1.apis.enderecos.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosCadastroEndereco;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("endereco")
public class EnderecoController {


    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEndereco dados) {


        List<String> violacoes = getViolacoes(dados);

        if ( isDadosOk(violacoes) ) {

            return ResponseEntity.ok().body("SUCESSO: ao cadastrar endereço");

        } else {

            return ResponseEntity.badRequest().body(errorMessage(violacoes));

        }


    }

    private static boolean isDadosOk(List<String> violacoes) {
        return violacoes.isEmpty();
    }

    private String errorMessage(List<String> violacoes) {

        StringBuilder sb = new StringBuilder();


        violacoes.stream().forEach(violacao -> {
            sb.append(violacao);
            sb.append("\n");
        });


        return sb.toString();

    }


    private <T> List<String> getViolacoes(T obj) {

        Set<ConstraintViolation<T>> violacoes = validator.validate(obj);

        List<String> violacoesToList = violacoes.stream().
                map((violacao) -> new String(violacao.getPropertyPath() + ":" + violacao.getMessage()))
                .collect(Collectors.toList());

        return violacoesToList;

    }


}
