package rm349040.techchallenge1.api.dtos.enderecos;

import rm349040.techchallenge1.domain.model.Endereco;

public record DadosListagemEndereco( Long id, String rua, String numero, String bairro, String cidade, String estado )  {

    public DadosListagemEndereco(Endereco endereco){
        this(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());
    }

}
