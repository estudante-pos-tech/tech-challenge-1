package rm349040.techchallenge1.util;

import jakarta.servlet.http.HttpServletRequest;

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

    static public String ERRO_ATUALIZAR(String dominio, String id) {
        return dominio + " NÃO atualizado, pois seu id=" + id + " NÃO existia na base de dados.";
    }


    public static String ERRO_ATUALIZAR() {
        return "Recurso NÃO atualizado, pois seu id NÃO existia na base de dados.";
    }

    static public String ERRO_EXCLUIR(String dominio, long id) {
        return dominio + " NÃO excluido, pois seu id=" + id + " NÃO existia na base de dados.";
    }

    public static String ERRO_EXCLUIR() {
        return "Recurso NÃO excluido, pois seu id NÃO existia na base de dados.";
    }


    static public String ERRO_ID_NULO(String dominio) {
        return "ERRO: O id do " + dominio + " NÃO pode ser nulo.";
    }

    static public String ERRO_CRIAR(String dominio, String msg) {
        return "ERRO: " + dominio + "" + msg;
    }


    static public String ERRO_METODO_REQUEST_NAO_SUPORTADO() {
        return "ERRO: Método Http NÃO suportado";
    }

    public static String ERRO_MENSAGEM_NAO_LEGIVEL(HttpServletRequest request, Exception ex) {
        String action="";

        switch (request.getMethod().toString()){
            case "GET"   :action="LISTAR";break;
            case "POST"  :action="CRIAR";break;
            case "PUT"   :action="ATUALIZAR";break;
            case "DELETE":action="EXCLUIR";break;
        }

        return "ERRO ao "+action+ " : "+ex.getMessage();
    }
}
