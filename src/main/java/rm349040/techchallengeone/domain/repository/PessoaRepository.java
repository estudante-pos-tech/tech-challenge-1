package rm349040.techchallengeone.domain.repository;

import rm349040.techchallengeone.domain.model.Parentesco;
import rm349040.techchallengeone.domain.model.Pessoa;
import rm349040.techchallengeone.domain.model.Sexo;

import java.time.LocalDate;

public class PessoaRepository extends Repositorio<Pessoa>{
    public PessoaRepository() {
        super(Pessoa.class);
        temporary();
    }
    private void temporary() {
        Pessoa temporary = Pessoa.builder()
                .id(5770425075213337291L)
                .nome("ana bela")
                .sexo(Sexo.FEMININO)
                .nascimento(LocalDate.of(2020,01,01))
                .parentesco(Parentesco.MAE)
                .build();
        collection.add(temporary);
    }
}
