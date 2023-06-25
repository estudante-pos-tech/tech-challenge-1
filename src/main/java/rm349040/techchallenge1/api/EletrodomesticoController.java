package rm349040.techchallenge1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.DadosAtualizarEletrodomestico;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.DadosCadastroEletrodomestico;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.DadosListagemEletrodomestico;
import rm349040.techchallenge1.domain.model.Eletrodomestico;
import rm349040.techchallenge1.domain.repository.Repositorio;
import rm349040.techchallenge1.util.Messages;
import rm349040.techchallenge1.util.Validation;

@RestController
@RequestMapping("eletrodomesticos")
public class EletrodomesticoController {


    @Autowired
    private Repositorio<Eletrodomestico> repositorio;

    @Autowired
    private Validation validation;


    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEletrodomestico dados) {


//        if (validation.throwExceptionIfDataIsWrong(dados)) {
//
//            repositorio.save(dados.toEletrodomestico());
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(Messages.SUCESSO_CRIAR(Eletrodomestico.class.getSimpleName()));
//
//        } else {
//
//            return ResponseEntity.badRequest().body(validation.errorMessage(dados));
//
//        }
        return null;

    }


    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizarEletrodomestico dados) {

//        if (validation.throwExceptionIfDataIsWrong(dados)) {
//
//
//            var eletrodomestico = repositorio.getReferenceById(dados.id());
//
//
//            if (eletrodomestico.isPresent()) {
//
//                eletrodomestico.get().atualizarDados(dados.toEletrodomestico());
//
//                return ResponseEntity.ok().body(dados);
//
//            } else {
//
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO(Eletrodomestico.class.getSimpleName(), dados.id()));
//
//            }
//
//
//        } else {
//
//            return ResponseEntity.badRequest().body(validation.errorMessage(dados));
//
//        }


        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        if (id != null) {

            var eletrodomestico = repositorio.deleteById(id);

            if (eletrodomestico.isPresent()) {

                return ResponseEntity.ok().body(Messages.SUCESSO_EXCLUJR(Eletrodomestico.class.getSimpleName(), id));

            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO_AO_EXCLUIR(Eletrodomestico.class.getSimpleName(), id));

            }

        } else{

            return ResponseEntity.badRequest().body(Messages.ERRO_ID_NULO(Eletrodomestico.class.getSimpleName()));

        }


    }



    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(repositorio.findAll().stream().map(DadosListagemEletrodomestico::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {

        var obj = repositorio.getReferenceById(id);

        if(obj.isPresent()){

            return ResponseEntity.ok().body(obj.stream().map(DadosListagemEletrodomestico::new));

        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO(Eletrodomestico.class.getSimpleName(), id));

        }

    }

}//FIM CRUD ELETRODOMÉSTICOS