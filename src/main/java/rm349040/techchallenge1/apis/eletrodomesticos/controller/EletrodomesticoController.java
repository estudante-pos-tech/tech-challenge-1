package rm349040.techchallenge1.apis.eletrodomesticos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.apis.eletrodomesticos.controller.dtos.DadosAtualizarEletrodomestico;
import rm349040.techchallenge1.apis.eletrodomesticos.controller.dtos.DadosCadastroEletrodomestico;
import rm349040.techchallenge1.apis.eletrodomesticos.controller.dtos.DadosListagemEletrodomestico;
import rm349040.techchallenge1.apis.eletrodomesticos.dominio.Eletrodomestico;
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


    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizarEletrodomestico dados) {

        if (validation.isDadosOk(dados)) {

            var eletrodomestico = repositorio.getReferenceById(dados.id());


            if (eletrodomestico.isPresent()) {

                eletrodomestico.get().atualizarDados(dados.toEletrodomestico());
                repositorio.save(eletrodomestico.get());

                return ResponseEntity.ok().body(dados);

            } else {
                return ResponseEntity.badRequest().body(Messages.ERRO_ATUALIZAR(Eletrodomestico.class.getSimpleName(),dados.id()));
            }


        } else {

            return ResponseEntity.badRequest().body(validation.errorMessage(dados));

        }

    }


    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(repositorio.findAll().stream().map(DadosListagemEletrodomestico::new));
    }


}
