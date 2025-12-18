package com.tuition.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex){
        log.error("Resource Not found: {} ",ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    public ResponseEntity<ApiError> handleGenric(Exception ex){
        log.error("Unexpected error ", ex);

        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Something went wrong .Please try again"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
