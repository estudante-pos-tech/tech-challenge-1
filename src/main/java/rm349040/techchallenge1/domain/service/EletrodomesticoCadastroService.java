package rm349040.techchallenge1.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import rm349040.techchallenge1.domain.model.Eletrodomestico;
import rm349040.techchallenge1.domain.repository.EletrodomesticoRepository;

public class EletrodomesticoCadastroService extends CadastroService<Eletrodomestico> {

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    public EletrodomesticoCadastroService() {
        super(Eletrodomestico.class);
        repositorio = eletrodomesticoRepository;
    }

}
