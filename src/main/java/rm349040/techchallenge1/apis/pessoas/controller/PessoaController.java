package rm349040.techchallenge1.apis.pessoas.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rm349040.techchallenge1.apis.pessoas.controller.dtos.DadosAtualizarPessoa;
import rm349040.techchallenge1.apis.pessoas.controller.dtos.DadosCadastroPessoa;
import rm349040.techchallenge1.apis.pessoas.controller.dtos.DadosListagemPessoa;
import rm349040.techchallenge1.apis.pessoas.dominio.Pessoa;
import rm349040.techchallenge1.repository.Repositorio;
import rm349040.techchallenge1.util.Messages;
import rm349040.techchallenge1.util.Validation;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("pessoa")
public class PessoaController  {


    @Autowired
    private Repositorio<Pessoa> repositorio;

    @Autowired
    private Validation validation;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroPessoa dados) {


        if (validation.isDadosOk(dados)) {

            repositorio.save(dados.toPessoa());

            return ResponseEntity.status(HttpStatus.CREATED).body(Messages.SUCESSO_CRIAR(Pessoa.class.getSimpleName()));

        } else {

            return ResponseEntity.badRequest().body(validation.errorMessage(dados));

        }

    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizarPessoa dados) {

        if (validation.isDadosOk(dados)) {

            var pessoa = repositorio.getReferenceById(dados.id());

            if (pessoa.isPresent()) {

                pessoa.get().atualizarDados(dados.toPessoa());

                return ResponseEntity.ok().body(dados);

            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO(Pessoa.class.getSimpleName(), dados.id()));

            }


        } else {

            return ResponseEntity.badRequest().body(validation.errorMessage(dados));

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {

        if (id != null) {

            var pessoa = repositorio.deleteById(id);

            if (pessoa.isPresent()) {

                return ResponseEntity.ok().body(Messages.SUCESSO_EXCLUJR(Pessoa.class.getSimpleName(), id));

            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO_AO_EXCLUIR(Pessoa.class.getSimpleName(), id));

            }

        } else{

            return ResponseEntity.badRequest().body(Messages.ERRO_ID_NULO(Pessoa.class.getSimpleName()));

        }


    }

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok().body(repositorio.findAll().stream().map(DadosListagemPessoa::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {

        var obj = repositorio.getReferenceById(id);

        if(obj.isPresent()){

            return ResponseEntity.ok().body(obj.stream().map(DadosListagemPessoa::new));

        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.NAO_ENCONTRADO(Pessoa.class.getSimpleName(), id));

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
