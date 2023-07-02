package rm349040.techchallengeone.domain.repository;

import rm349040.techchallengeone.domain.model.Endereco;

public class EnderecoRepository extends Repositorio<Endereco>{
    public EnderecoRepository() {
        super(Endereco.class);
        temporary();
    }

    private void temporary() {
        Endereco temporary = Endereco.builder()
                .id(2848467342194617996L)
                .rua("rua bela")
                .numero("234")
                .bairro("bairro")
                .cidade("Maya")
                .estado("AM")
                .build();
        collection.add(temporary);
    }
}
