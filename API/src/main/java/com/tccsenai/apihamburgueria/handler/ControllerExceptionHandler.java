package com.tccsenai.apihamburgueria.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity tratarValidacoes(MethodArgumentNotValidException e) {
        Map<String, String> erros = new HashMap<>();
        for (int i = 0; i < e.getBindingResult().getAllErrors().size(); i++) {

            String campo = ((FieldError) e.getBindingResult().getAllErrors().get(i)).getField();
            String erro = ((FieldError) e.getBindingResult().getAllErrors().get(i)).getDefaultMessage();

            erros.put(String.format("Erro: " + i), String.format("Erro no campo: " + campo.toUpperCase() + " - " + erro));

        }
        return  new ResponseEntity(erros, HttpStatus.BAD_REQUEST);
    }
}
