package rm349040.techchallenge1.apis.enderecos.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosAtualizarEndereco;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosCadastroEndereco;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosListagemEndereco;
import rm349040.techchallenge1.apis.enderecos.dominio.Endereco;
import rm349040.techchallenge1.repositories.Repositorio;
import rm349040.techchallenge1.util.Messages;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("endereco")
public class EnderecoController {


    @Autowired
    private Repositorio<Endereco> repositorio;

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEndereco dados) {


        List<String> violacoes = getViolacoes(dados);

        if (isDadosOk(violacoes)) {

            repositorio.save(dados.toEndereco());

            return ResponseEntity.ok().body(Messages.SUCESSO_CRIAR(Endereco.class.getName()));

        } else {

            return ResponseEntity.badRequest().body(errorMessage(violacoes));

        }


    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizarEndereco dados) {

        List<String> violacoes = getViolacoes(dados);

        if (isDadosOk(violacoes)) {

            var endereco = repositorio.getReferenceById(dados.id());


            if (endereco.isPresent()) {

                endereco.get().atualizarDados(dados.toEndereco());
                repositorio.save(endereco.get());

                return ResponseEntity.ok().body(dados);

            } else {
                return ResponseEntity.badRequest().body(Messages.ERRO_ATUALIZAR(Endereco.class.getName(),dados.id()));
            }


        } else {

            return ResponseEntity.badRequest().body(errorMessage(violacoes));

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        if (id != null) {


            var endereco = repositorio.getReferenceById(id);

            if (endereco.isPresent()) {

                repositorio.delete(endereco.get());

                return ResponseEntity.ok().body(Messages.SUCESSO_EXCLUJR(Endereco.class.getName(), id));

            } else {
                return ResponseEntity.badRequest().body(Messages.ERRO_EXCLUIR(Endereco.class.getName(),id));
            }

        } else{

            return ResponseEntity.badRequest().body(Messages.ERRO_ID_NULO(Endereco.class.getName()));

        }


    }


    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(repositorio.findAll().stream().map(DadosListagemEndereco::new));
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
