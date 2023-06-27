package rm349040.techchallenge1.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErrorType {

    ENTITY_NOT_FOUND("/entidade-nao-encontrada", "Entidade não encotrada");

    private String uri = "https://github.com/estudante-pos-tech/tech-challenge-1";
    private String title;

    ErrorType(String path, String title) {
        uri += path;
        this.title = title;
    }
}
