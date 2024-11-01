package org.tatastrive.callbackapi.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message","validation failed, please check your payload");
        Map<String, String> errors = new HashMap<String, String>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        response.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFound(EntityNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message","No record was found ");
        response.put("details",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<Object> handleInsertDuplicateRecordError(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message","Engagement Id Already exists : "+ ex.getCause());
        response.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }
//    @ExceptionHandler(ConstraintViolationException.class)
//    public String handleInsertDuplicationError(ConstraintViolationException ex){
//        String message = "Duplicate record found";
//        return message+ ex.getMessage();
//    }
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Object> handleGenericError(Exception ex) {
//        Map<String, Object> response = new HashMap<String, Object>();
//        response.put("message","An unexpected error occurred");
//        response.put("details", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }

}
