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
    private CadastroService<Pessoa> pessoaCadastroService;

    @Autowired
    private Mapper mapper;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosPessoaCriada criar(@RequestBody DadosCadastroPessoa dados) {

        Pessoa pessoa = mapper.toDomain(dados, Pessoa.class);

        pessoa = pessoaCadastroService.criar(pessoa);

        DadosPessoaCriada output = mapper.toDto(pessoa, DadosPessoaCriada.class);

        return output;

    }

    @PutMapping("/{id}")
    public DadosPessoaAtualizada atualizar(@PathVariable Long id, @RequestBody DadosAtualizarPessoa dados) {

        Pessoa pessoa = mapper.toDomain(dados, Pessoa.class);

        pessoa.setId(id);

        pessoa = pessoaCadastroService.atualizarOuFalhar(pessoa);

        DadosPessoaAtualizada output = mapper.toDto(pessoa,DadosPessoaAtualizada.class);

        return output;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        pessoaCadastroService.excluir(id);
    }

    @GetMapping
    public Set<DadosListagemPessoa> listar() {

        Set<Pessoa> pessoas = pessoaCadastroService.listar();
        Set<DadosListagemPessoa> pessoasListagem= pessoas.stream().map(DadosListagemPessoa::new).collect(Collectors.toSet());

        return pessoasListagem;

    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Optional.of(pessoaCadastroService.listarById(id)).stream().map(DadosListagemPessoa::new));
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
