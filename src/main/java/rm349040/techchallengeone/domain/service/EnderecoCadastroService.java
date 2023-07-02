package rm349040.techchallengeone.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import rm349040.techchallengeone.domain.exception.DomainException;
import rm349040.techchallengeone.domain.exception.EnderecoNotFoundException;
import rm349040.techchallengeone.domain.model.Endereco;
import rm349040.techchallengeone.domain.repository.EnderecoRepository;

public class EnderecoCadastroService extends CadastroService<Endereco> {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoCadastroService(){
        super(Endereco.class);
        repositorio = enderecoRepository;
    }

    @Override
    protected DomainException entityNotFoundException(Long id) {
        return new EnderecoNotFoundException(String.format(ENTITY_NOT_FOUND_MSG(),id));
    }

}
