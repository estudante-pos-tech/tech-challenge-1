package rm349040.techchallenge1.api.exceptionhandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rm349040.techchallenge1.domain.exception.EntityNotFoundException;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorType errorType = ErrorType.PARAMETRO_INVALIDO;

        String detail = String.format("O token '%s' da URL recebeu o valor '%s' que é um tipo inválido." +
                " Corrija e informe um valor compatível com o tipo %s",ex.getPropertyName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        ApiError error = newApiBuilder(HttpStatus.valueOf(status.value()), errorType, detail)
                .timeStamp(Instant.now())
                .build();

        return handleExceptionInternal(ex,error,headers,status,request);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof JsonParseException) {
            return handleJsonParseException((JsonParseException) rootCause, headers, status, request);
        }

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        if(rootCause instanceof PropertyBindingException){
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        String detail = "O body da request está inválido. Corrija erro de sintaxe";


        ApiError apiError = newApiBuilder(HttpStatus.valueOf(status.value()), ErrorType.MESSAGE_NOT_READABLE, detail)
                .timeStamp(Instant.now())
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorType errorType = ErrorType.MESSAGE_NOT_READABLE;

        String detail = String.format("A propriedade '%s' não é permitida." +
                " Corrija excluindo-a.",ex.getPropertyName());

        ApiError error = newApiBuilder(HttpStatus.valueOf(status.value()), errorType, detail)
                .timeStamp(Instant.now())
                .build();

        return handleExceptionInternal(ex,error,headers,status,request);

    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ErrorType errorType = ErrorType.MESSAGE_NOT_READABLE;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é um tipo inválido." +
                " Corrija e informe um valor compatível com o tipo %s",path, ex.getValue(), ex.getTargetType().getSimpleName());

        ApiError error = newApiBuilder(HttpStatus.valueOf(status.value()), errorType, detail)
                .timeStamp(Instant.now())
                .build();

        return handleExceptionInternal(ex,error,headers,status,request);

    }


    private ResponseEntity handleJsonParseException(JsonParseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        if(ex instanceof JsonEOFException){

            ErrorType errorType = ErrorType.MESSAGE_NOT_READABLE;
            String detail = "O corpo da solicitação está mal escrito. Corrija adicionando '}' ao fim da mensagem.";

            ApiError error = newApiBuilder(HttpStatus.valueOf(status.value()), errorType, detail)
                    .timeStamp(Instant.now())
                    .build();

            return handleExceptionInternal(ex,error,headers,status,request);

        }


        ErrorType errorType = ErrorType.MESSAGE_NOT_READABLE;
        String detail = "Corrija o corpo da solicitação. Ele está mal escrito" ;

        ApiError error = newApiBuilder(HttpStatus.valueOf(status.value()), errorType, detail)
                .timeStamp(Instant.now())
                .build();


        return handleExceptionInternal(ex,error,headers,status,request);

        //cant get unexpected token even expected values directly from JsonParseException...
        //so i wrote this workaround... ;)
//        String unexpectedToken = ex.getLocalizedMessage().split(" ")[2];
//        String values = ex.getLocalizedMessage().split("\\)")[0];
//        values = values.split("\\(")[1];
//
//        String expectedValue="";
//        for(String s : values.split(" ")){
//            expectedValue+=s;
//        }
//
//        expectedValue = expectedValue.replace("JSON","");
//
//        ErrorType errorType = ErrorType.MESSAGE_NOT_READABLE;
//        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é um tipo inválido." +
//                " Corrija e informe um valor compatível com o tipo %s",ex.getProcessor().getParsingContext().getCurrentName(), unexpectedToken, expectedValue);
//
//        ApiError error = newApiBuilder(HttpStatus.valueOf(status.value()), errorType, detail)
//                .timeStamp(Instant.now())
//                .build();
//
//
//        return handleExceptionInternal(ex,error,headers,status,request);

    }



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {


        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage() + ". Tentando te ajudar ... passe um id que exista na base de dados que daí você poderá receber o que solicita.";

        ApiError apiError = newApiBuilder(status, ErrorType.ENTITY_NOT_FOUND, detail)
                .timeStamp(Instant.now())
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) {

            body = ApiError.builder().
                    title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase()).
                    status(statusCode.value()).
                    timeStamp(Instant.now()).
                    build();

        } else if (body instanceof String) {

            body = ApiError.builder().
                    title((String) body).
                    timeStamp(Instant.now()).
                    status(statusCode.value()).
                    build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ApiError.ApiErrorBuilder newApiBuilder(HttpStatus status, ErrorType errorType, String detail) {
        return ApiError.builder()
                .status(status.value())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(detail);
    }

}
