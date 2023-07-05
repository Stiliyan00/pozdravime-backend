package com.tutorial.employeemanagmentbackend.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    EMAIL_NULL(101),
    GREETING_TEXT_NULL(102),
    ACTOR_NULL(103),

    INTERNAL_SERVER_ERROR(201),
    CANNOT_SEND_EMAIL(202);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static HttpStatus getHttpStatus(ErrorCode errorCode) {
        if (errorCode.code >= 100 && errorCode.code < 200) {
            return HttpStatus.BAD_REQUEST;
        } else if (errorCode.code >= 200 && errorCode.code < 300) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
