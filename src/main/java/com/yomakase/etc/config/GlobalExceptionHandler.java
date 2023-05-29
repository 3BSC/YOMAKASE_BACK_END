package com.yomakase.etc.config;

import com.yomakase.etc.enums.ExceptionMessage;
import com.yomakase.etc.exception.BaseException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseException> defaultException(Throwable e, HandlerMethod handlerMethod) throws IOException {

        BaseException baseException;
        if (e instanceof BaseException) {
            ((BaseException) e).setErrorTrace(e.getStackTrace()[0].toString());

            baseException = (BaseException) e;
        } else {
            baseException = new BaseException(e.getClass().getSimpleName(), ExceptionMessage.UNDEFINED_EXCEPTION);
            baseException.setErrorMessage(e.getMessage());
            baseException.setErrorTrace(e.getStackTrace()[0].toString());
        }

        return new ResponseEntity<>(baseException, baseException.getHttpStatus());
    }
}
