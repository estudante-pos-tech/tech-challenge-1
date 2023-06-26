package rm349040.techchallenge1.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rm349040.techchallenge1.api.dtos.pessoas.DadosAtualizarPessoa;
import rm349040.techchallenge1.api.dtos.pessoas.DadosCadastroPessoa;
import rm349040.techchallenge1.api.dtos.pessoas.output.DadosListagemPessoa;
import rm349040.techchallenge1.api.dtos.pessoas.output.DadosPessoaAtualizada;
import rm349040.techchallenge1.api.dtos.pessoas.output.DadosPessoaCriada;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;
import rm349040.techchallenge1.domain.exception.IdNullException;
import rm349040.techchallenge1.domain.model.Pessoa;
import rm349040.techchallenge1.domain.service.CadastroService;
import rm349040.techchallenge1.util.Mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pessoas")
public class PessoaController  {


    @Autowired
    private CadastroService<Pessoa> cadastroService;

    @Autowired
    private Mapper mapper;
    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroPessoa dados) {


        Pessoa pessoa = mapper.fromDtoToDomain(dados, Pessoa.class);

        pessoa = cadastroService.criar(pessoa);

        DadosPessoaCriada output = mapper.fromDomainToDto(pessoa, DadosPessoaCriada.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);


    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizarPessoa dados) {

        try {

            Pessoa pessoa = mapper.fromDtoToDomain(dados, Pessoa.class);

            pessoa.setId(id);

            pessoa = cadastroService.atualizarOuFalhar(pessoa);

            DadosPessoaAtualizada output = mapper.fromDomainToDto(pessoa,DadosPessoaAtualizada.class);

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

        Set<Pessoa> pessoas = cadastroService.listar();
        Set<DadosListagemPessoa> pessoasListagem= pessoas.stream().map(DadosListagemPessoa::new).collect(Collectors.toSet());

        return ResponseEntity.ok(pessoasListagem);

    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {

        try {

            return ResponseEntity.ok().body(Optional.of(cadastroService.listarById(id)).stream().map(DadosListagemPessoa::new));

        }catch (EntityNotFoundException e){

            return ResponseEntity.notFound().build();

        }catch (IdNullException e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }


    /**
     * @param request 
     * @param response
     * @param handler
     * @param ex
     * @return
     */

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        System.out.println("RESOLVEEXCEPTION");

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        Map body = new LinkedHashMap<>();
//        body.put(“timestamp”, LocalDateTime.now());
        body.put("message",ex.getMessage());
        return new ModelAndView("error", body);

    }

  }//FIM CRUD
