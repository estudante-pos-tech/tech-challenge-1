package rm349040.techchallenge1.domain.repository;

import rm349040.techchallenge1.domain.model.Eletrodomestico;
import rm349040.techchallenge1.domain.model.Pessoa;
import rm349040.techchallenge1.domain.model.Sexo;

import java.time.LocalDate;

public class EletrodomesticoRepository extends Repositorio<Eletrodomestico>{
    public EletrodomesticoRepository() {
        super(Eletrodomestico.class);
        //temporary();
    }
    private void temporary() {
        Eletrodomestico temporary = Eletrodomestico.builder()
                .id(2848467342194617996L)
                .nome("")
                .modelo("")
                .potencia("")
                .build();
        collection.add(temporary);
    }
}
