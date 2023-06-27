package rm349040.techchallenge1.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import rm349040.techchallenge1.domain.model.Pessoa;
import rm349040.techchallenge1.domain.repository.PessoaRepository;

public class PessoaCadastroService extends CadastroService<Pessoa>{

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaCadastroService() {
        super(Pessoa.class);
        repositorio = pessoaRepository;
    }
}
