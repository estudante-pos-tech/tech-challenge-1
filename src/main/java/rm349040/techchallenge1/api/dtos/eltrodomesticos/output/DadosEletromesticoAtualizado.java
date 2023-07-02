package rm349040.techchallenge1.api.dtos.eltrodomesticos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosEletromesticoAtualizado {

    private Long id;
    private String nome;
    private String modelo;
    private Double potencia;

}
