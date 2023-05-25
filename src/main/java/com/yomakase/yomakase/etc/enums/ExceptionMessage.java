package com.yomakase.yomakase.etc.enums;

public enum ExceptionMessage {
    ;


    private String message;
    private Integer code;

    ExceptionMessage(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
