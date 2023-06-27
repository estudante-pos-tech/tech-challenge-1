package rm349040.techchallenge1.domain.repository;


import org.springframework.stereotype.Repository;
import rm349040.techchallenge1.domain.BASE;

import java.util.*;

@Repository
//@Scope("prototype")
public abstract class Repositorio<T extends BASE> {

    protected Class<T> entityClass;

    static private String MSG_NULL_ID = "O id não pode ser nulo";

    static private int instances = 0;

    private static Random random = new Random();

    private Set<T> collection = new HashSet<>();;

    public Repositorio(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T t) {

        if (t.getId() == null) {//creating

            t.setId(random.nextLong(Long.MAX_VALUE));
            collection.add(t);

        }

        return t;

    }

    public Optional<T> getReferenceById(Long id) {

        if (id == null) throw new NullPointerException(MSG_NULL_ID);

        return collection.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Set<T> findAll() {
        return Collections.unmodifiableSet(collection);
    }

    private void delete(T t) {
        collection.remove(t);
    }

    public Optional<T> deleteById(Long id) {

        if (id != null) {

            Optional<T> optional = getReferenceById(id);

            if (optional.isPresent()) {

                delete(optional.get());

                return optional;

            } else {

                return Optional.empty();

            }

        } else {

           throw new NullPointerException(MSG_NULL_ID);

        }

    }
}
