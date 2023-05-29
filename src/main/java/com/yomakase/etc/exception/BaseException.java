package com.yomakase.etc.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yomakase.etc.enums.ExceptionMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "message", "suppressed"})
public class BaseException extends RuntimeException {

    protected String className;
    protected String errorMessage;
    protected Integer code;
    protected String ErrorTrace;
    protected HttpStatus httpStatus;

    public BaseException(ExceptionMessage errorMessage) {
        this.className = this.getClass().getSimpleName();
        this.errorMessage = errorMessage.getMessage();
        this.code = errorMessage.getCode();
        this.httpStatus = errorMessage.getStatus();
    }

    public BaseException(String className, ExceptionMessage errorMessage) {
        this(errorMessage);
        this.className = className;
    }
}
