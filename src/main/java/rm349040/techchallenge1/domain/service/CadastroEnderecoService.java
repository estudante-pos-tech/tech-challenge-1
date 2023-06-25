package rm349040.techchallenge1.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rm349040.techchallenge1.domain.exception.DomainException;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;
import rm349040.techchallenge1.domain.exception.EntityNullException;
import rm349040.techchallenge1.domain.exception.IdNullException;
import rm349040.techchallenge1.domain.model.Endereco;
import rm349040.techchallenge1.domain.repository.Repositorio;
import rm349040.techchallenge1.util.Mapper;
import rm349040.techchallenge1.util.Validation;

import java.util.Set;

@Service
public class CadastroEnderecoService {

    private static String ENTITY_NOT_FOUND = "Endereço não atualizado, pois o id %d não existia na base de dados";

    @Autowired
    private Repositorio<Endereco> repositorio;

    @Autowired
    private Validation validation;

    @Autowired
    private Mapper mapper;

    public Endereco criar(Endereco endereco) {

        return repositorio.save(endereco);

    }
   public Endereco atualizarOuFalhar(Endereco atual){

        if(atual == null){
            throw new EntityNullException("O Endereço não pode ser nulo");
        }

        if( atual.getId() == null){
            throw new IdNullException(("O id do Endereço não pode ser nulo."));
        }

        Endereco endereco = repositorio.
                getReferenceById(atual.getId()).
                    orElseThrow(
                            () ->  {return entityNotFoundException(atual.getId());});

        //simula atualizacao
        mapper.identify(atual,endereco);


        return endereco;
   }

    public void excluir(Long id) {

        try {

            repositorio.deleteById(id).orElseThrow(() -> entityNotFoundException(id));

        }catch (NullPointerException e){

            throw new IdNullException(e.getMessage());
        }


    }

    private DomainException entityNotFoundException(Long id) {
        return new EntityNotFoundException(String.format(ENTITY_NOT_FOUND,id));
    }

    public Set<Endereco> listar() {
        return repositorio.findAll();
    }

    public Endereco listarById(Long id) {

        try {

            return repositorio.getReferenceById(id).orElseThrow(() -> {return entityNotFoundException(id);});

        }catch (NullPointerException e){

            throw new IdNullException(e.getMessage());

        }

    }
}