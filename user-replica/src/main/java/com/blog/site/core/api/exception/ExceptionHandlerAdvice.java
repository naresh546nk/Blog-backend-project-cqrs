package com.blog.site.core.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        log.info("errors :"+errors);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
//    }
//

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({QueryExecutionException.class})
    public ResponseEntity<Map<String, String>> queryExecutionException(
            QueryExecutionException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());
        log.info("errors :"+errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }




    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericExceptions(
            Exception ex) {
        Map<String, String> errors = new HashMap<>();
            errors.put("message", ex.getMessage());
            log.debug("Error :"+ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
