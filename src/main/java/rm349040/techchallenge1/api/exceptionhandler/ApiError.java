package rm349040.techchallenge1.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@ToString
public class ApiError {

    private Integer status;
    private Instant timeStamp;
    private String type;
    private String title;
    private String detail;

    //TODO implmeentar essa mensagem no GlobalExceptionHandler.java
    private String userMessage;
    private String path;
    private List<Field> fields;


    @Getter
    @Builder
    public static class Field{
        private String name;
        private String userMessage;
    }


}
