package rm349040.techchallenge1.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rm349040.techchallenge1.domain.BASE;
import rm349040.techchallenge1.domain.exception.DomainException;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;
import rm349040.techchallenge1.domain.exception.EntityNullException;
import rm349040.techchallenge1.domain.exception.IdNullException;
import rm349040.techchallenge1.domain.model.Endereco;
import rm349040.techchallenge1.domain.repository.Repositorio;
import rm349040.techchallenge1.util.Mapper;

import java.util.Set;

@Service
public class CadastroService<T extends BASE> {

    private Class<T> type;
    private String ENTITY_NOT_FOUND = getType() + " não atualizado(a), pois o id %d não existia na base de dados";

    private String getType() {

        return "Entidade";
    }

    @Autowired
    private Repositorio<T> repositorio;

    @Autowired
    private Mapper mapper;

    public T criar(T t) {

        return repositorio.save(t);

    }
   public T atualizarOuFalhar(T atual){

        if(atual == null){
            throw new EntityNullException("A entidade não pode ser nulo");
        }

        if( atual.getId() == null){
            throw new IdNullException(("O id dentidade não pode ser nulo."));
        }

        T t = repositorio.
                getReferenceById(atual.getId()).
                    orElseThrow(
                            () ->  {return entityNotFoundException(atual.getId());});

        //simula atualizacao
        mapper.identify(atual,t);


        return t;
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

    public Set<T> listar() {
        return repositorio.findAll();
    }

    public T listarById(Long id) {

        try {

            return repositorio.getReferenceById(id).orElseThrow(() -> {return entityNotFoundException(id);});

        }catch (NullPointerException e){

            throw new IdNullException(e.getMessage());

        }

    }

    public static void main(String[] args) {
        new CadastroService<Endereco>().getType();
    }
}