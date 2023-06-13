package rm349040.techchallenge1.apis.pessoas.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import rm349040.techchallenge1.apis.enderecos.dominio.Endereco;
import rm349040.techchallenge1.apis.pessoas.controller.dtos.DadosCadastroPessoa;
import rm349040.techchallenge1.repositories.Repositorio;
import rm349040.techchallenge1.util.Messages;
import rm349040.techchallenge1.util.Validation;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pessoa")
public class PessoaController  {


    @Autowired
    private Repositorio<Endereco> repositorio;

    @Autowired
    private Validation validation;

    @PostMapping
    public ResponseEntity criar(@RequestBody DadosCadastroPessoa dados) {


        if (validation.isDadosOk(dados)) {

            //repositorio.save(dados.toPessoa());

            //TODO refatorar para Pessoa.class.getName()
            return ResponseEntity.ok().body(Messages.SUCESSO_CRIAR("Pessoa"));

        } else {

            return ResponseEntity.badRequest().body(validation.errorMessage(dados));

        }


    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody ResponseEntity handleHttpMessageNotReadableException(HttpServletRequest request, Exception ex){
        //TODO refatorar para Pessoa.class.getSimpleName()
        return ResponseEntity.badRequest().body(Messages.ERRO_CRIAR("Pessoa", ": sexo e/ou parentesco inválidos"));
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
}
