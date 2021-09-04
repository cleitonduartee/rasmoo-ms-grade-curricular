package com.cleiton.duartee.cliente.escola.gradecurricular.handler;

import com.cleiton.duartee.cliente.escola.gradecurricular.exception.MateriaException;
import com.cleiton.duartee.cliente.escola.gradecurricular.model.ErrorMapResponse;
import com.cleiton.duartee.cliente.escola.gradecurricular.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException materiaException){

        return ResponseEntity.status(materiaException.getHttpStatus()).body(
                ErrorResponse
                        .builder()
                        .httpStatus(materiaException.getHttpStatus().value())
                        .mensagem(materiaException.getMessage())
                        .timeStamp(System.currentTimeMillis())
                        .build()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException validation){
        Map<String,String> erroValidation = new HashMap<>();
        validation.getBindingResult().getAllErrors().forEach(erro ->{
            String campo = ((FieldError)erro).getField();
            String messagem = erro.getDefaultMessage();
            erroValidation.put(campo,messagem);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorMapResponse.builder()
                        .mensagem("Erros de validações.")
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .timeStamp(System.currentTimeMillis())
                        .erros(erroValidation)
                        .build()
        );
    }
}
