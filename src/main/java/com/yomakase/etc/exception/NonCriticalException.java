package com.yomakase.etc.exception;

import com.yomakase.etc.enums.ExceptionMessage;

public class NonCriticalException extends BaseException {

    public NonCriticalException(String className, ExceptionMessage errorMessage) {
        super(className, errorMessage);
    }

    public NonCriticalException(ExceptionMessage errorMessage) {
        super(errorMessage);
    }
}
