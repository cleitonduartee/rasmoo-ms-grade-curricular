package com.cleiton.duartee.cliente.escola.gradecurricular.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class Response<T> extends RepresentationModel<Response<T>> {
    private int status;
    private T data;
}
