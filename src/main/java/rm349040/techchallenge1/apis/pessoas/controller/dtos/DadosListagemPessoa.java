package rm349040.techchallenge1.apis.pessoas.controller.dtos;

import rm349040.techchallenge1.apis.pessoas.dominio.Parentesco;
import rm349040.techchallenge1.apis.pessoas.dominio.Pessoa;
import rm349040.techchallenge1.apis.pessoas.dominio.Sexo;

import java.time.LocalDate;

public record DadosListagemPessoa(Long id, String nome, LocalDate nascimento, Sexo sexo, Parentesco parentesco) {

    public DadosListagemPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(),pessoa.getNascimento(), pessoa.getSexo(), pessoa.getParentesco());
    }

}
