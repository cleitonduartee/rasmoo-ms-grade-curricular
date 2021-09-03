package com.cleiton.duartee.cliente.escola.gradecurricular.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private String mensagem;
    private int httpStatus;
    private Long timeStamp;
}
