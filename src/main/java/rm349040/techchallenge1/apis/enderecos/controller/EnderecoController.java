package rm349040.techchallenge1.apis.enderecos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosAtualizarEndereco;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosCadastroEndereco;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosListagemEndereco;
import rm349040.techchallenge1.apis.enderecos.dominio.Endereco;
import rm349040.techchallenge1.repository.Repositorio;
import rm349040.techchallenge1.util.Messages;
import rm349040.techchallenge1.util.Validation;

@RestController
@RequestMapping("endereco")
public class EnderecoController {


    @Autowired
    private Repositorio<Endereco> repositorio;


    @Autowired
    private Validation validation;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEndereco dados) {


        if (validation.isDadosOk(dados)) {

            repositorio.save(dados.toEndereco());

            return ResponseEntity.ok().body(Messages.SUCESSO_CRIAR(Endereco.class.getSimpleName()));

        } else {

            return ResponseEntity.badRequest().body(validation.errorMessage(dados));

        }


    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizarEndereco dados) {

        if (validation.isDadosOk(dados)) {

            var endereco = repositorio.getReferenceById(dados.id());


            if (endereco.isPresent()) {

                endereco.get().atualizarDados(dados.toEndereco());
                repositorio.save(endereco.get());

                return ResponseEntity.ok().body(dados);

            } else {
                return ResponseEntity.badRequest().body(Messages.ERRO_ATUALIZAR(Endereco.class.getSimpleName(),dados.id()));
            }


        } else {

            return ResponseEntity.badRequest().body(validation.errorMessage(dados));

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        if (id != null) {

            var endereco = repositorio.deleteById(id);

            if (endereco.isPresent()) {

                return ResponseEntity.ok().body(Messages.SUCESSO_EXCLUJR(Endereco.class.getSimpleName(), id));

            } else {
                return ResponseEntity.badRequest().body(Messages.ERRO_EXCLUIR(Endereco.class.getSimpleName(),id));
            }

        } else{

            return ResponseEntity.badRequest().body(Messages.ERRO_ID_NULO(Endereco.class.getSimpleName()));

        }


    }


    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(repositorio.findAll().stream().map(DadosListagemEndereco::new));
    }





}
