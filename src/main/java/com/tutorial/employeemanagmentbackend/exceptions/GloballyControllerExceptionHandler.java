package com.tutorial.employeemanagmentbackend.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GloballyControllerExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GloballyControllerExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> handleBusinessRuleException(BusinessException ex,
                                                                 WebRequest webRequest) {
        logAppropriateLevel(ex);
        ErrorResponse body = new ErrorResponse(ex.getMessage(), ex.getErrorCode().getCode());
        HttpStatus httpStatus = ErrorCode.getHttpStatus(ex.getErrorCode());

        return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, webRequest);
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleException(RuntimeException ex,
                                                     WebRequest webRequest) {
        logger.error("Internal error {}", ex.getMessage(), ex);
        ErrorResponse body = new ErrorResponse("Internal server error", 500);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, webRequest);
    }

    private void logAppropriateLevel(BusinessException ex) {
        if (ErrorCode.getHttpStatus(ex.getErrorCode()) == HttpStatus.BAD_REQUEST) {
            logger.info("Bad request received {}", ex.getMessage());
        } else {
            logger.error("Internal error {}", ex.getMessage(), ex);
        }
    }
}
