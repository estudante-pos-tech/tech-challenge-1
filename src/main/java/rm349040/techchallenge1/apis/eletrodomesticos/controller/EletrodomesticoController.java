package rm349040.techchallenge1.apis.eletrodomesticos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.apis.eletrodomesticos.controller.dtos.DadosCadastroEletrodomestico;
import rm349040.techchallenge1.apis.eletrodomesticos.controller.dtos.DadosListagemEletrodomestico;
import rm349040.techchallenge1.apis.eletrodomesticos.dominio.Eletrodomestico;
import rm349040.techchallenge1.apis.pessoas.controller.dtos.DadosListagemPessoa;
import rm349040.techchallenge1.repository.Repositorio;
import rm349040.techchallenge1.util.Messages;
import rm349040.techchallenge1.util.Validation;

@RestController
@RequestMapping("eletrodomestico")
public class EletrodomesticoController {


    @Autowired
    private Repositorio<Eletrodomestico> repositorio;

    @Autowired
    private Validation validation;


    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEletrodomestico dados) {


        if (validation.isDadosOk(dados)) {

            repositorio.save(dados.toEletrodomestico());

            return ResponseEntity.ok().body(Messages.SUCESSO_CRIAR(Eletrodomestico.class.getSimpleName()));

        } else {

            return ResponseEntity.badRequest().body(validation.errorMessage(dados));

        }

    }


    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(repositorio.findAll().stream().map(DadosListagemEletrodomestico::new));
    }


}
