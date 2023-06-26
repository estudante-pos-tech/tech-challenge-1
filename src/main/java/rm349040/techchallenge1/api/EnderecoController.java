package rm349040.techchallenge1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.api.dtos.enderecos.DadosAtualizarEndereco;
import rm349040.techchallenge1.api.dtos.enderecos.DadosCadastroEndereco;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosEnderecoAtualizado;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosEnderecoCriado;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosListagemEndereco;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;
import rm349040.techchallenge1.domain.exception.IdNullException;
import rm349040.techchallenge1.domain.model.Endereco;
import rm349040.techchallenge1.domain.service.CadastroService;
import rm349040.techchallenge1.util.Mapper;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {


    @Autowired
    private CadastroService<Endereco> cadastroService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEndereco dados) {


        Endereco endereco = mapper.toDomain(dados, Endereco.class);

        endereco = cadastroService.criar(endereco);

        DadosEnderecoCriado output = mapper.toDto(endereco, DadosEnderecoCriado.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);


    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizarEndereco dados) {

        try {

            Endereco endereco = mapper.toDomain(dados, Endereco.class);

            endereco.setId(id);

            endereco = cadastroService.atualizarOuFalhar(endereco);

            DadosEnderecoAtualizado output = mapper.toDto(endereco,DadosEnderecoAtualizado.class);

            return ResponseEntity.ok(output);

        } catch (EntityNotFoundException ex) {

            return ResponseEntity.notFound().build();

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        try {

            cadastroService.excluir(id);

            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException ex) {

            return ResponseEntity.notFound().build();

        } catch (IdNullException ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());

        }


    }


    @GetMapping
    public ResponseEntity listar() {

        Set<Endereco> enderecos = cadastroService.listar();
        Set<DadosListagemEndereco> enderecosListagem= enderecos.stream().map(DadosListagemEndereco::new).collect(Collectors.toSet());

        return ResponseEntity.ok(enderecosListagem);

    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {

        try {

            return ResponseEntity.ok().body(Optional.of(cadastroService.listarById(id)).stream().map(DadosListagemEndereco::new));

        }catch (EntityNotFoundException e){

            return ResponseEntity.notFound().build();

        }catch (IdNullException e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }


}
