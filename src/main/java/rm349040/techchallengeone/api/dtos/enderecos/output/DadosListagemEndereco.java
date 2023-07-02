package rm349040.techchallengeone.api.dtos.enderecos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rm349040.techchallengeone.domain.model.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosListagemEndereco  {

    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    public DadosListagemEndereco(Endereco endereco){
        this(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());
    }

}
