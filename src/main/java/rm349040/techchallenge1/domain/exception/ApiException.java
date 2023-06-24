package rm349040.techchallenge1.domain.exception;

public class ApiException extends RuntimeException{

    private String error;
    private Integer status;

    public ApiException(){}

    public ApiException(String error, String msg,Integer status){
        super(msg);
        this.error = error;
        this.status = status;
    }


    public String getError() {
        return error;
    }

    public Integer getStatus() {
        return status;
    }
}
