package rm349040.techchallenge1.domain.repository;

import rm349040.techchallenge1.domain.model.Eletrodomestico;

public class EletrodomesticoRepository extends Repositorio<Eletrodomestico>{
    public EletrodomesticoRepository() {
        super(Eletrodomestico.class);
        temporary();
    }
    private void temporary() {
        Eletrodomestico temporary = Eletrodomestico.builder()
                .id(3863405005119453053L)
                .nome("eletrodomesticos")
                .modelo("casa-casa")
                .potencia(0.2)
                .build();
        collection.add(temporary);
    }
}
