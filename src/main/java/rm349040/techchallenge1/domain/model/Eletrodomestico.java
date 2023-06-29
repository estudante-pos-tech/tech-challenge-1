package rm349040.techchallenge1.domain.model;


import lombok.*;
import rm349040.techchallenge1.domain.BASE;

import java.util.Random;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class Eletrodomestico extends BASE<Eletrodomestico> {

    private Long id;
    private String nome;
    private String modelo;
    private String potencia;

    /**
     * @param eletrodomestico
     */
    @Override
    public void atualizarDados(Eletrodomestico eletrodomestico) {
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
    }

    /**
     *
     */
    @Override
    public void setDefault() {

        Random r = new Random();

        long id = r.nextLong(Long.MAX_VALUE);

        this.id = id;
        nome = "Eletro "+id;
        modelo = "modelo "+id;
        potencia = "0."+id+" KWh";

    }
}
