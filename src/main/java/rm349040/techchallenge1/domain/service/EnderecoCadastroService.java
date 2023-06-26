package rm349040.techchallenge1.domain.service;

import rm349040.techchallenge1.domain.exception.DomainException;
import rm349040.techchallenge1.domain.exception.EnderecoNotFoundException;
import rm349040.techchallenge1.domain.model.Endereco;

public class EnderecoCadastroService extends CadastroService<Endereco> {

    public EnderecoCadastroService(){
        super(Endereco.class);
    }

    @Override
    protected DomainException entityNotFoundException(Long id) {
        return new EnderecoNotFoundException(ENTITY_NOT_FOUND_MSG());
    }
}
