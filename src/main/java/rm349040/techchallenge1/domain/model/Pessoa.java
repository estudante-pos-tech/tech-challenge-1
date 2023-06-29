package rm349040.techchallenge1.domain.model;

import lombok.*;
import rm349040.techchallenge1.domain.BASE;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class Pessoa extends BASE<Pessoa> {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Sexo sexo;
    private Parentesco parentesco;

    /**
     * @param pessoa
     */
    @Override
    public void atualizarDados(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.nascimento = pessoa.getNascimento();
        this.sexo = pessoa.getSexo();
        this.parentesco = pessoa.getParentesco();
    }

    /**
     *
     */
    @Override
    public void setDefault() {

        Random r = new Random();

        long id = r.nextLong(Long.MAX_VALUE);

        this.id = id;
        nome = "Pessoa "+id;
        int year = 1900 + (int)(id%200);
        nascimento = LocalDate.of(year,(int)id%12,(int)id%30);
        sexo = id%2 == 0 ? Sexo.MASCULINO : Sexo.FEMININO;
        parentesco = id%2==1 ? Parentesco.MAE : Parentesco.PAI;

    }
}
