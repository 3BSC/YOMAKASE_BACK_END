package com.yomakase.yomakase.etc.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionMessage {
    UNDEFINED_EXCEPTION(0, "정의되지 않은 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NULL_POINTER_EXCEPTION(1, "NULL 여부를 확인해주세요.", HttpStatus.BAD_REQUEST),

    // User 관련 예외
    USER_NOT_EXISTS_EXCEPTION(1000, "해당 유저를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXIST(1001, "이미 존재하는 유저입니다.", HttpStatus.CONFLICT),


    // 인증, 인가 관련 예외
    INVALID_TOKEN(12, "유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS(13, "유효하지 않은 접근입니다.", HttpStatus.CONFLICT),
    EXPIRED_TOKEN(14, "만료된 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN_TYPE(15,  "잘못된 토큰 타입입니다.", HttpStatus.BAD_REQUEST),
    
    

    ;


    private String message;
    private Integer code;
    private HttpStatus status;

    ExceptionMessage(Integer code, String message, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
