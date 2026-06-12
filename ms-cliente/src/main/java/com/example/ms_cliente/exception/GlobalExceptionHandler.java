package com.example.ms_cliente.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<?> manejarNotFound(ResourceNotFoundException ex){

        log.error(ex.getMessage());
        return ResponseEntity
        .status(HttpStatus.SC_NOT_FOUND)
        .body(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<?> validar(MethodArgumentNotValidException ex){

        Map<String,String> errores=
        new HashMap<>();

        ex.getBindingResult()
        .getFieldErrors()
        .forEach(error->{errores.put(
            error.getField(),
            error.getDefaultMessage());
        });
        return ResponseEntity
        .badRequest()
        .body(errores);
    }

}
