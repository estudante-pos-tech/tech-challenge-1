package rm349040.techchallenge1.repository;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import rm349040.techchallenge1.apis.BASE;

import java.util.*;

@Repository
@Scope("prototype")
public class Repositorio<T extends BASE> {

    static private int instances = 0;

    private static Random random = new Random();

    private Set<T> collection;

    public Repositorio() {
        collection = new HashSet<>();
        System.out.println("CREATING REPOSITORY " + ++instances + " Set type is " + collection.getClass().getSimpleName() + " " + collection.getClass().toGenericString());
    }

    public T save(T t) {

        if (t.getId() == null) {//creating

            t.setId(random.nextLong(Long.MAX_VALUE));
            collection.add(t);

        }

        return t;

    }

    public Optional<T> getReferenceById(Long id) {
        return collection.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Set<T> findAll() {
        return Collections.unmodifiableSet(collection);
    }

    public void delete(T t) {
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

            return Optional.empty();

        }

    }
}
