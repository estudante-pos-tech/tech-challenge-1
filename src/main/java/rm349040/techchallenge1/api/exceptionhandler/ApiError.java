package rm349040.techchallenge1.api.exceptionhandler;

import lombok.*;

import java.time.Instant;

@Getter
@Builder
@ToString
public class ApiError {

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
