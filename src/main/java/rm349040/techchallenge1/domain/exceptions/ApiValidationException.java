package rm349040.techchallenge1.domain.exceptions;

public class ApiValidationException extends RuntimeException{

    private String error;
    private Integer status;

    public ApiValidationException(){}

    public ApiValidationException(String error, String msg,Integer status){
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
