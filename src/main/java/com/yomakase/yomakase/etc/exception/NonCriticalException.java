package com.yomakase.yomakase.etc.exception;

import com.yomakase.yomakase.etc.enums.ExceptionMessage;

public class NonCriticalException extends BaseException {

    public NonCriticalException(String className, ExceptionMessage errorMessage) {
        super(className, errorMessage);
    }

    public NonCriticalException(ExceptionMessage errorMessage) {
        super(errorMessage);
    }
}
