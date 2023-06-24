package rm349040.techchallenge1.domain;

public abstract class BASE<T> {
    abstract public void setId(Long id);
    abstract public Long getId();
    abstract public void atualizarDados(T t);
}
