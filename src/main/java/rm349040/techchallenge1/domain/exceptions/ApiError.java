package rm349040.techchallenge1.domain.exceptions;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ApiError {

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
