package rm349040.techchallenge1.api.dtos.eltrodomesticos.output;

import rm349040.techchallenge1.domain.model.Eletrodomestico;

public record DadosListagemEletrodomestico(Long id, String nome, String modelo, Double potencia) {

    public DadosListagemEletrodomestico(Eletrodomestico eletrodomestico){
        this(eletrodomestico.getId(), eletrodomestico.getNome(), eletrodomestico.getModelo(), eletrodomestico.getPotencia());
    }

}
