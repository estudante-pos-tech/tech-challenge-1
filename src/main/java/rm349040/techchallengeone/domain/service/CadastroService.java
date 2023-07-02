package rm349040.techchallengeone.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rm349040.techchallengeone.domain.BASE;
import rm349040.techchallengeone.domain.exception.DomainException;
import rm349040.techchallengeone.domain.exception.EntityNotFoundException;
import rm349040.techchallengeone.domain.exception.EntityNullException;
import rm349040.techchallengeone.domain.exception.IdNullException;
import rm349040.techchallengeone.domain.repository.Repositorio;
import rm349040.techchallengeone.util.Mapper;

import java.util.Set;

@Service
public abstract class CadastroService<T extends BASE> {

    protected Class<T> entityClass;

    public CadastroService(Class<T> entityClass) {

        this.entityClass = entityClass;
        this.type = this.entityClass;
    }

    private Class<T> type;

    @Autowired
    protected Repositorio<T> repositorio;

    @Autowired
    private Mapper mapper;

    private CRUD acao = CRUD.LISTAR;

    public T criar(T t) {
        acao = CRUD.CREATE;
        return repositorio.save(t);
    }


    public T atualizarOuFalhar(T atual){

        acao = CRUD.UPDATE;

        if(atual == null){
            throw new EntityNullException(getType() + " não pode ser nulo");
        }

        if( atual.getId() == null){
            throw new IdNullException(("O id do(a) "+getType()+" não pode ser nulo."));
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

            acao = CRUD.DELETE;

            repositorio.deleteById(id).orElseThrow(() -> entityNotFoundException(id));

        }catch (NullPointerException e){

            throw new IdNullException(e.getMessage());
        }


    }

    public Set<T> listar() {
        return repositorio.findAll();
    }

    public T listarById(Long id) {

        try {

            acao = CRUD.LISTAR;

            return repositorio.getReferenceById(id).orElseThrow(() -> {return entityNotFoundException(id);});

        }catch (NullPointerException e){

            throw new IdNullException(e.getMessage());

        }

    }


    protected DomainException entityNotFoundException(Long id) {
        return new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MSG(),id));
    }

    private String getType() {

        return type==null? "Entidade":type.getSimpleName();
    }

    protected String ENTITY_NOT_FOUND_MSG(){
    return  getType() + " não "+ acao.getAction().toLowerCase()+", pois o id %d não existia na base de dados";
    }

//    public static void main(String[] args) {
//        new CadastroService<Endereco>().getParameterType().getSimpleName();
//    }
}