package rm349040.techchallengeone.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import rm349040.techchallengeone.domain.model.Pessoa;
import rm349040.techchallengeone.domain.repository.PessoaRepository;

public class PessoaCadastroService extends CadastroService<Pessoa>{

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaCadastroService() {
        super(Pessoa.class);
        repositorio = pessoaRepository;
    }
}
