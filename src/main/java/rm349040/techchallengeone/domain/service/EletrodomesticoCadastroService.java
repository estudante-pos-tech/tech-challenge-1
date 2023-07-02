package rm349040.techchallengeone.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import rm349040.techchallengeone.domain.model.Eletrodomestico;
import rm349040.techchallengeone.domain.repository.EletrodomesticoRepository;

public class EletrodomesticoCadastroService extends CadastroService<Eletrodomestico> {

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    public EletrodomesticoCadastroService() {
        super(Eletrodomestico.class);
        repositorio = eletrodomesticoRepository;
    }

}
