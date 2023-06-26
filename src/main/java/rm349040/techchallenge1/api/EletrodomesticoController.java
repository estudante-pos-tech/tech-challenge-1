package rm349040.techchallenge1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.DadosAtualizarEletrodomestico;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.DadosCadastroEletrodomestico;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.output.DadosListagemEletrodomestico;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.output.DadosEletromesticoAtualizado;
import rm349040.techchallenge1.api.dtos.eltrodomesticos.output.DadosEletromesticoCriado;
import rm349040.techchallenge1.api.dtos.enderecos.output.DadosEnderecoAtualizado;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;
import rm349040.techchallenge1.domain.exception.IdNullException;
import rm349040.techchallenge1.domain.model.Eletrodomestico;
import rm349040.techchallenge1.domain.service.CadastroService;
import rm349040.techchallenge1.util.Mapper;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("eletrodomesticos")
public class EletrodomesticoController {


    @Autowired
    private CadastroService<Eletrodomestico> eletrodomesticoCadastroService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosEletromesticoCriado criar(@RequestBody DadosCadastroEletrodomestico dados) {

        Eletrodomestico eletrodomestico = mapper.toDomain(dados, Eletrodomestico.class);

        eletrodomestico = eletrodomesticoCadastroService.criar(eletrodomestico);

        DadosEletromesticoCriado output = mapper.toDto(eletrodomestico, DadosEletromesticoCriado.class);

        return output;

    }


    @PutMapping("/{id}")
    public DadosEletromesticoAtualizado atualizar(@PathVariable Long id, @RequestBody DadosAtualizarEletrodomestico dados) {

        Eletrodomestico eletrodomestico = toDomain(dados);

        eletrodomestico.setId(id);

        eletrodomestico = eletrodomesticoCadastroService.atualizarOuFalhar(eletrodomestico);

        DadosEletromesticoAtualizado output = mapper.toDto(eletrodomestico,DadosEletromesticoAtualizado.class);

        return output;

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        eletrodomesticoCadastroService.excluir(id);
    }


    @GetMapping
    public Set<DadosListagemEletrodomestico> listar() {

        Set<Eletrodomestico> eletrodomesticos = eletrodomesticoCadastroService.listar();
        Set<DadosListagemEletrodomestico> eletromesticosListagem= eletrodomesticos.stream().map(DadosListagemEletrodomestico::new).collect(Collectors.toSet());

        return eletromesticosListagem;

    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Optional.of(eletrodomesticoCadastroService.listarById(id)).stream().map(DadosListagemEletrodomestico::new));
    }

    private Eletrodomestico toDomain(Object dto){
        return mapper.toDomain(dto, Eletrodomestico.class);
    }

}//FIM CRUD ELETRODOMÉSTICOS
