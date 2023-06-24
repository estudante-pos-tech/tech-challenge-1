package rm349040.techchallenge1.domain.model;

import lombok.*;
import rm349040.techchallenge1.domain.BASE;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class Endereco extends BASE<Endereco> {

    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;


    public static void main(String[] args) {
        System.out.println(new Endereco(3L, "rua", "numero", "centro", "sp", "sp"));
    }


    @Override
    public void atualizarDados(Endereco endereco) {

        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();

    }

    /**
     *
     */
    @Override
    public void setDefault() {

        Random r = new Random();

        long id = r.nextLong(Long.MAX_VALUE);

        this.id = id;
        rua = "Rua "+id;
        numero = "Numero "+id;
        bairro = "Bairro "+id;
        cidade = "Cidade "+id;
        estado = "Estado "+id;


    }
}
