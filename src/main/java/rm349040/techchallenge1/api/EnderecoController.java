package rm349040.techchallenge1.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.api.dtos.enderecos.DadosAtualizarEndereco;
import rm349040.techchallenge1.api.dtos.enderecos.DadosCadastroEndereco;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosEnderecoAtualizado;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosEnderecoCriado;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosListagemEndereco;
import rm349040.techchallenge1.domain.model.Endereco;
import rm349040.techchallenge1.domain.service.CadastroService;
import rm349040.techchallenge1.util.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {


    @Autowired
    private CadastroService<Endereco> enderecoCadastroService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosEnderecoCriado criar(@RequestBody @Valid DadosCadastroEndereco dados) {


        Endereco endereco = mapper.toDomain(dados, Endereco.class);

        endereco = enderecoCadastroService.criar(endereco);

        DadosEnderecoCriado output = mapper.toDto(endereco, DadosEnderecoCriado.class);

        return output;


    }

    @PutMapping("/{id}")
    public DadosEnderecoAtualizado atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizarEndereco dados) {

        Endereco endereco = mapper.toDomain(dados, Endereco.class);

        endereco.setId(id);

        endereco = enderecoCadastroService.atualizarOuFalhar(endereco);

        DadosEnderecoAtualizado output = mapper.toDto(endereco,DadosEnderecoAtualizado.class);

        return output;

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        enderecoCadastroService.excluir(id);
    }


    @GetMapping
    public Set<DadosListagemEndereco> listar() {

        Set<Endereco> enderecos = enderecoCadastroService.listar();
        Set<DadosListagemEndereco> enderecosListagem= enderecos.stream().map(DadosListagemEndereco::new).collect(Collectors.toSet());

        return enderecosListagem;

    }


    @GetMapping("/{id}")
    public DadosListagemEndereco listarById(@PathVariable Long id) {
        return mapper.toDto(enderecoCadastroService.listarById(id),DadosListagemEndereco.class);
    }



}
