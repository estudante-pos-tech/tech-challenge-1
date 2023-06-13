package rm349040.techchallenge1.repositories;


import org.springframework.stereotype.Repository;
import rm349040.techchallenge1.apis.BASE;

import java.util.*;

@Repository
public class Repositorio<T extends BASE> {

    private static Random random = new Random();

    private Set<T> collection;

    public Repositorio() {
        collection = new HashSet<>();
        System.out.println("CREATING REPOSITORY " + collection.getClass().componentType() + " " + collection.getClass().toGenericString());
    }

    public T save(T t) {

        if (t.getId() == null) {//creating

            t.setId(random.nextLong(Long.MAX_VALUE));
            collection.add(t);


        } else {//updating

            getReferenceById(t.getId()).get().atualizarDados(t);

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

}
