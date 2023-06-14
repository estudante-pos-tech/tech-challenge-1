package rm349040.techchallenge1.apis.eletrodomesticos.dominio;


import lombok.*;
import rm349040.techchallenge1.apis.BASE;

@Getter
@Setter
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
}
