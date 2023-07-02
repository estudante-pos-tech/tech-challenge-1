package rm349040.techchallenge1.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import rm349040.techchallenge1.domain.exception.DomainException;
import rm349040.techchallenge1.domain.exception.EnderecoNotFoundException;
import rm349040.techchallenge1.domain.model.Endereco;
import rm349040.techchallenge1.domain.repository.EnderecoRepository;

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
