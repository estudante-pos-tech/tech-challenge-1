package rm349040.techchallenge1.api.dtos.pessoas.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rm349040.techchallenge1.domain.model.Parentesco;
import rm349040.techchallenge1.domain.model.Pessoa;
import rm349040.techchallenge1.domain.model.Sexo;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosListagemPessoa {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Sexo sexo;
    private Boolean isUsuario;
    private Parentesco parentesco;
    public DadosListagemPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(),pessoa.getNascimento(), pessoa.getSexo(), pessoa.getIsUsuario(), pessoa.getParentesco());
    }

}
