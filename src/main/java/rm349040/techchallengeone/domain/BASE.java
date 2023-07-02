package rm349040.techchallengeone.domain;

public abstract class BASE<T> {
    abstract public void setId(Long id);

    abstract public Long getId();

    abstract public void atualizarDados(T t);
}