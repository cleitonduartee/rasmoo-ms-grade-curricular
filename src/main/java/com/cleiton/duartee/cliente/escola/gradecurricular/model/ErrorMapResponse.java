package com.cleiton.duartee.cliente.escola.gradecurricular.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;


@Getter
@SuperBuilder
public class ErrorMapResponse extends ErrorResponse {

   private Map<String, String> erros;
}
