package rm349040.techchallenge1.api.dtos.pessoas;

import rm349040.techchallenge1.domain.model.Parentesco;
import rm349040.techchallenge1.domain.model.Pessoa;
import rm349040.techchallenge1.domain.model.Sexo;

import java.time.LocalDate;

public record DadosListagemPessoa(Long id, String nome, LocalDate nascimento, Sexo sexo, Parentesco parentesco) {

    public DadosListagemPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(),pessoa.getNascimento(), pessoa.getSexo(), pessoa.getParentesco());
    }

}
