package com.cleiton.duartee.cliente.escola.gradecurricular.handler;

import com.cleiton.duartee.cliente.escola.gradecurricular.exception.MateriaException;
import com.cleiton.duartee.cliente.escola.gradecurricular.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
