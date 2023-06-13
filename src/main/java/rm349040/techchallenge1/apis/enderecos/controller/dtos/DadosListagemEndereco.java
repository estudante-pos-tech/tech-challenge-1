package rm349040.techchallenge1.apis.enderecos.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rm349040.techchallenge1.apis.enderecos.dominio.Endereco;

public record DadosListagemEndereco( Long id, String rua, String numero, String bairro, String cidade, String estado )  {

    public DadosListagemEndereco(Endereco endereco){
        this(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());
    }

}
