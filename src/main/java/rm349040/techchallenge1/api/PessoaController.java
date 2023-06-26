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
import rm349040.techchallenge1.api.dtos.pessoas.output.DadosPessoaCriada;
import rm349040.techchallenge1.domain.model.Pessoa;
import rm349040.techchallenge1.domain.service.CadastroService;
import rm349040.techchallenge1.util.Mapper;

import java.util.LinkedHashMap;
import java.util.Map;

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

    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizarPessoa dados) {

//        if (validation.throwExceptionIfDataIsWrong(dados)) {
//
//            var pessoa = repositorio.getReferenceById(dados.id());
//
//            if (pessoa.isPresent()) {
//
//                pessoa.get().atualizarDados(dados.toPessoa());
//
//                return ResponseEntity.ok().body(dados);
//
//            } else {
//
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO(Pessoa.class.getSimpleName(), dados.id()));
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

//        if (id != null) {
//
//            var pessoa = repositorio.deleteById(id);
//
//            if (pessoa.isPresent()) {
//
//                return ResponseEntity.ok().body(Messages.SUCESSO_EXCLUJR(Pessoa.class.getSimpleName(), id));
//
//            } else {
//
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO_AO_EXCLUIR(Pessoa.class.getSimpleName(), id));
//
//            }
//
//        } else{
//
//            return ResponseEntity.badRequest().body(Messages.ERRO_ID_NULO(Pessoa.class.getSimpleName()));
//
//        }
        return null;


    }

    @GetMapping
    public ResponseEntity listar() {
        //return ResponseEntity.ok().body(repositorio.findAll().stream().map(DadosListagemPessoa::new));
        return null;
    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {

//        var obj = repositorio.getReferenceById(id);
//
//        if(obj.isPresent()){
//
//            return ResponseEntity.ok().body(obj.stream().map(DadosListagemPessoa::new));
//
//        }else{
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO(Pessoa.class.getSimpleName(), id));
//
//        }
        return null;

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
