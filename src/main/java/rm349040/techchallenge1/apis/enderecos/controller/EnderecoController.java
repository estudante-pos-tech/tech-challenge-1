package rm349040.techchallenge1.apis.enderecos.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rm349040.techchallenge1.apis.enderecos.controller.dtos.DadosCadastroEndereco;

@RestController
@RequestMapping("endereco")
public class EnderecoController {


    @PostMapping
    public String criar(@RequestBody @Valid DadosCadastroEndereco dados){


        return "OK";


    }



}
