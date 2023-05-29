package com.yomakase.etc.exception;

import com.yomakase.etc.enums.ExceptionMessage;

public class CriticalException extends BaseException {

    public CriticalException(String className, ExceptionMessage errorMessage) {
        super(className, errorMessage);
    }

    public CriticalException(ExceptionMessage errorMessage) {
        super(errorMessage);
    }
}
