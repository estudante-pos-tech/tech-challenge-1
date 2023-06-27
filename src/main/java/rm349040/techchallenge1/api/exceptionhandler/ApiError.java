package rm349040.techchallenge1.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@ToString
public class ApiError {

    private Instant timeStamp;

    private Integer status;
    private String type;
    private String title;
    private String detail;
//
    private String path;

}
