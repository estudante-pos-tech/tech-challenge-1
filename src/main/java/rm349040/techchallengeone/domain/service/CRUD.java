package rm349040.techchallengeone.domain.service;

public enum CRUD {


CREATE("Criado(a)"),UPDATE("Atualizado(a)"),DELETE("Excluido(a)"),
LISTAR("Listado(a)");

    private String action;

    CRUD(String action) {
        this.action = action;
    }

    public String getAction(){
        return action;
    }
}
