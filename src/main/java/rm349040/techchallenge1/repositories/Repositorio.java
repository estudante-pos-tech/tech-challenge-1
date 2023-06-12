package rm349040.techchallenge1.repositories;


import org.springframework.stereotype.Repository;
import rm349040.techchallenge1.apis.ID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Repository
public class Repositorio<T extends ID> {

    private static Random random = new Random();

    private Set<T> collection;

    public Repositorio() {
        collection = new HashSet<>();
        System.out.println("CREATING REPOSITORY " + collection.getClass().componentType() + " " + collection.getClass().toGenericString() );
    }

    public T save(T t){

        t.setId(random.nextLong(Long.MAX_VALUE));
        collection.add(t);
        return t;

    }

    public Set<T> getAll(){
        return Collections.unmodifiableSet(collection);
    }

}
