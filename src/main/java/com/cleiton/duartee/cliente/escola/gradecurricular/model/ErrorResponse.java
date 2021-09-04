package com.cleiton.duartee.cliente.escola.gradecurricular.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ErrorResponse {
    private String mensagem;
    private int httpStatus;
    private Long timeStamp;
}
