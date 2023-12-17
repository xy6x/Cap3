package com.example.rentingcars.Advise;

import com.example.rentingcars.ApiException.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        String message =e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException a){
        String message =a.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException a){
        String message =a.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException a){
        String message =a.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException a){
        String message =a.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException a){
        String message =a.getMessage();
        return ResponseEntity.status(400).body(message);

    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(msg);
    }
}

