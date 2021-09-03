package com.cleiton.duartee.cliente.escola.gradecurricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MateriaException extends RuntimeException{

    private final HttpStatus httpStatus;

    public MateriaException (final String msg, final HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }

}
