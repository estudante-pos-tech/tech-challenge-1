package rm349040.techchallenge1.util;

public class Messages {

    static public String SUCESSO_CRIAR(String dominio) {
        return "SUCESSO: ao criar " + dominio;
    }

    static public String SUCESSO_EXCLUJR(String dominio, long id) {
        return "SUCESSO: ao excluir " + dominio + " " + id;
    }


    static public String ERRO_ATUALIZAR(String dominio, long id) {
        return dominio + " NÃO atualizado, pois seu id=" + id + " NÃO existia na base de dados.";
    }

    static public String ERRO_EXCLUIR(String dominio, long id) {
        return dominio + " NÃO excluido, pois seu id=" + id + " NÃO existia na base de dados.";
    }


    static public String ERRO_ID_NULO(String dominio) {
        return "ERRO: O id do "+dominio+" NÃO pode ser nulo.";
    }




}
