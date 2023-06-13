package rm349040.techchallenge1.repositories;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import rm349040.techchallenge1.apis.ID;

import java.util.*;

@Repository
public class Repositorio<T extends ID> {

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

    public Set<T> getAll() {
        return Collections.unmodifiableSet(collection);
    }

    public Optional<T> getReferenceById(Long id) {
        return collection.stream().filter(t -> t.getId().equals(id)).findFirst();
    }
}
