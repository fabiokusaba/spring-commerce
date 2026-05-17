package com.artefatox.sbm.catalog.api.handler;

import com.artefatox.sbm.catalog.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> runtimeExceptionHandler(RuntimeException ex) {
        ApiError error = new ApiError(ex, HttpStatus.BAD_REQUEST.value(), "OPERATION_FAILED");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiError> domainExceptionHandler(DomainException ex) {
        ApiError error = new ApiError(ex.getStatus(), ex.getCode(), ex.getMessage(), List.of());
        HttpStatus httpStatus = Optional.ofNullable(HttpStatus.resolve(ex.getStatus()))
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<ApiError.Field> fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {
            return ApiError.Field.builder()
                    .name(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }).toList();
        ApiError error = new ApiError(fields);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
