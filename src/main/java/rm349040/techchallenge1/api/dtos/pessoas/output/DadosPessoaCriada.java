package rm349040.techchallenge1.api.dtos.pessoas.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rm349040.techchallenge1.domain.model.Parentesco;
import rm349040.techchallenge1.domain.model.Sexo;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosPessoaCriada {
    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Sexo sexo;
    private Parentesco parentesco;
}
