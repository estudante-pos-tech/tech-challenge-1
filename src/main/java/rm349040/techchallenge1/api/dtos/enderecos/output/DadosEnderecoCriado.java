package rm349040.techchallenge1.api.dtos.enderecos.output;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rm349040.techchallenge1.domain.model.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosEnderecoCriado{


    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;



}
