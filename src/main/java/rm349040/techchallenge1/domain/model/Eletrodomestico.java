package rm349040.techchallenge1.domain.model;


import lombok.*;
import rm349040.techchallenge1.domain.BASE;

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
    private Double potencia;

    /**
     * @param eletrodomestico
     */
    @Override
    public void atualizarDados(Eletrodomestico eletrodomestico) {
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
    }

}
