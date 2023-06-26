package rm349040.techchallenge1.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rm349040.techchallenge1.domain.BASE;
import rm349040.techchallenge1.domain.exceptions.DomainException;
import rm349040.techchallenge1.domain.exceptions.EntityNotFoundException;
import rm349040.techchallenge1.domain.exceptions.EntityNullException;
import rm349040.techchallenge1.domain.exceptions.IdNullException;
import rm349040.techchallenge1.domain.repository.Repositorio;
import rm349040.techchallenge1.util.Mapper;

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
    private Repositorio<T> repositorio;

    @Autowired
    private Mapper mapper;

    public T criar(T t) {
        return repositorio.save(t);
    }


    public T atualizarOuFalhar(T atual){

        if(atual == null){
            throw new EntityNullException(getType() + " não pode ser nulo");
        }

        if( atual.getId() == null){
            throw new IdNullException(("O id "+getType()+" não pode ser nulo."));
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

    protected DomainException entityNotFoundException(Long id) {
        return new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MSG(),id));
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

    private String getType() {

        return type==null? "Entidade":type.getSimpleName();
    }

    protected String ENTITY_NOT_FOUND_MSG(){
        return  getType() + " não atualizado(a), pois o id %d não existia na base de dados";
    }

//    public static void main(String[] args) {
//        new CadastroService<Endereco>().getParameterType().getSimpleName();
//    }
}