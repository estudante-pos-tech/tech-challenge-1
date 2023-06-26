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
import rm349040.techchallenge1.domain.exceptions.EntityNotFoundException;
import rm349040.techchallenge1.domain.exceptions.IdNullException;
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
    private CadastroService<Eletrodomestico> pessoaCadastroService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroEletrodomestico dados) {

        Eletrodomestico eletrodomestico = mapper.toDomain(dados, Eletrodomestico.class);

        eletrodomestico = pessoaCadastroService.criar(eletrodomestico);

        DadosEletromesticoCriado output = mapper.toDto(eletrodomestico, DadosEletromesticoCriado.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);

    }


    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizarEletrodomestico dados) {

        try {

            Eletrodomestico eletrodomestico = toDomain(dados);

            eletrodomestico.setId(id);

            eletrodomestico = pessoaCadastroService.atualizarOuFalhar(eletrodomestico);

            DadosEletromesticoAtualizado output = mapper.toDto(eletrodomestico,DadosEletromesticoAtualizado.class);

            return ResponseEntity.ok(output);

        } catch (EntityNotFoundException ex) {

            return ResponseEntity.notFound().build();

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        try {

            pessoaCadastroService.excluir(id);

            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException ex) {

            return ResponseEntity.notFound().build();

        } catch (IdNullException ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());

        }


    }



    @GetMapping
    public ResponseEntity listar() {

        Set<Eletrodomestico> eletrodomesticos = pessoaCadastroService.listar();
        Set<DadosListagemEletrodomestico> eletromesticosListagem= eletrodomesticos.stream().map(DadosListagemEletrodomestico::new).collect(Collectors.toSet());

        return ResponseEntity.ok(eletromesticosListagem);

    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {

        try {

            return ResponseEntity.ok().body(Optional.of(pessoaCadastroService.listarById(id)).stream().map(DadosListagemEletrodomestico::new));

        }catch (EntityNotFoundException e){

            return ResponseEntity.notFound().build();

        }catch (IdNullException e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }

    private Eletrodomestico toDomain(Object dto){
        return mapper.toDomain(dto, Eletrodomestico.class);
    }

}//FIM CRUD ELETRODOMÉSTICOS
