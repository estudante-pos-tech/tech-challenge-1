package rm349040.techchallenge1.api.dtos.eltrodomesticos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rm349040.techchallenge1.domain.model.Eletrodomestico;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosListagemEletrodomestico{

    private Long id;
    private String nome;
    private String modelo;
    private Double potencia;

    public DadosListagemEletrodomestico(Eletrodomestico eletrodomestico){
        this(eletrodomestico.getId(), eletrodomestico.getNome(), eletrodomestico.getModelo(), eletrodomestico.getPotencia());
    }

}
