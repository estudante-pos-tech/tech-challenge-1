package rm349040.techchallenge1.apis.eletrodomesticos.controller.dtos;

import rm349040.techchallenge1.apis.eletrodomesticos.dominio.Eletrodomestico;
import rm349040.techchallenge1.apis.pessoas.controller.dtos.DadosListagemPessoa;

public record DadosListagemEletrodomestico(Long id, String nome, String modelo, String potencia) {

    public DadosListagemEletrodomestico(Eletrodomestico eletrodomestico){
        this(eletrodomestico.getId(), eletrodomestico.getNome(), eletrodomestico.getModelo(), eletrodomestico.getPotencia());
    }

}
