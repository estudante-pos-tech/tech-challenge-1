package rm349040.techchallenge1.api.dtos.eltrodomesticos;

import rm349040.techchallenge1.domain.model.Eletrodomestico;

public record DadosListagemEletrodomestico(Long id, String nome, String modelo, String potencia) {

    public DadosListagemEletrodomestico(Eletrodomestico eletrodomestico){
        this(eletrodomestico.getId(), eletrodomestico.getNome(), eletrodomestico.getModelo(), eletrodomestico.getPotencia());
    }

}
