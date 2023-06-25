package rm349040.techchallenge1.api;

import jakarta.websocket.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.api.dtos.enderecos.DadosAtualizarEndereco;
import rm349040.techchallenge1.api.dtos.enderecos.DadosCadastroEndereco;
import rm349040.techchallenge1.api.dtos.enderecos.DadosListagemEndereco;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosEnderecoCriado;
import rm349040.techchallenge1.domain.exception.DomainException;
import rm349040.techchallenge1.domain.model.Endereco;
import rm349040.techchallenge1.domain.service.CadastroEnderecoService;
import rm349040.techchallenge1.util.Mapper;
import rm349040.techchallenge1.util.Messages;

import java.util.Optional;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {


    @Autowired
    private CadastroEnderecoService cadastroService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEndereco dados) {


        Endereco endereco = mapper.fromDtoToDomain(dados,Endereco.class);

        endereco = cadastroService.criar(endereco);

        DadosEnderecoCriado output = mapper.fromDomainToDto(endereco, DadosEnderecoCriado.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);


    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizarEndereco dados) {

        try {

            Endereco endereco = mapper.fromDtoToDomain(dados,Endereco.class);

            endereco.setId(id);

            endereco = cadastroService.atualizarOuFalhar(endereco);

            return ResponseEntity.ok().body(endereco);

        }catch (DomainException domainException){

            return ResponseEntity.notFound().build();

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        cadastroService.excluir(id);

        return ResponseEntity.ok(Messages.SUCESSO_EXCLUJR(Endereco.class.getSimpleName(),id));

    }


    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(cadastroService.listar().stream().map(DadosListagemEndereco::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Optional.of(cadastroService.listarById(id)).stream().map(DadosListagemEndereco::new));
    }



}
