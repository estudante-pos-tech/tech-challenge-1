package rm349040.techchallenge1.apis;

public abstract class ID<T> {
    abstract public void setId(Long id);
    abstract public Long getId();

    abstract public void atualizarDados(T t);
}
