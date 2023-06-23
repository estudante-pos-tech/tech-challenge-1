package rm349040.techchallenge1.apis.enderecos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosAtualizarEndereco;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosCadastroEndereco;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosListagemEndereco;
import rm349040.techchallenge1.apis.enderecos.dominio.Endereco;
import rm349040.techchallenge1.apis.enderecos.service.EnderecoService;
import rm349040.techchallenge1.util.Messages;

import java.util.Optional;

@RestController
@RequestMapping("endereco")
public class EnderecoController {


    @Autowired
    private EnderecoService service;


    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEndereco dados) {
        return ResponseEntity.ok(Messages.SUCESSO_CRIAR(Endereco.class.getSimpleName() + " id: "+service.criar(dados).getId()));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizarEndereco dados) {

        service.atualizar(dados);

        return ResponseEntity.ok().body(dados);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.ok(Messages.SUCESSO_EXCLUJR(Endereco.class.getSimpleName(),id));

    }


    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(service.listar().stream().map(DadosListagemEndereco::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Optional.of(service.listarById(id)).stream().map(DadosListagemEndereco::new));
    }



}
