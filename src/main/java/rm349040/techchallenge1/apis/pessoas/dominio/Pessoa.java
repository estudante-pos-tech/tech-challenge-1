package rm349040.techchallenge1.apis.pessoas.dominio;

import lombok.*;
import rm349040.techchallenge1.apis.BASE;

import java.time.LocalDate;

@Getter
@Setter
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
}
