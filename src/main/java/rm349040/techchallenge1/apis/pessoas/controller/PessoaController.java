package rm349040.techchallenge1.apis.pessoas.controller;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rm349040.techchallenge1.apis.enderecos.dominio.Endereco;
import rm349040.techchallenge1.apis.pessoas.controller.dtos.DadosCadastroPessoa;
import rm349040.techchallenge1.repositories.Repositorio;
import rm349040.techchallenge1.util.Messages;
import rm349040.techchallenge1.util.Validation;

import java.util.List;

@RestController
@RequestMapping("pessoa")
public class PessoaController {


    @Autowired
    private Repositorio<Endereco> repositorio;

    @Autowired
    private Validation validation;

//    @PostMapping
//    public ResponseEntity criar(@RequestBody DadosCadastroPessoa dados) {
//
//
//        List<String> violacoes = getViolacoes(dados);
//
//        if (isDadosOk(violacoes)) {
//
//            repositorio.save(dados.toPessoa());
//
//            //TODO refatorar para Pessoa.class.getName()
//            return ResponseEntity.ok().body(Messages.SUCESSO_CRIAR("Pessoa"));
//
//        } else {
//
//            return ResponseEntity.badRequest().body(errorMessage(violacoes));
//
//        }
//
//
//    }


}
