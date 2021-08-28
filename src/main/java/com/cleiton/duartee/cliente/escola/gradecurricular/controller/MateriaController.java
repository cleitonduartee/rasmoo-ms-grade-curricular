package com.cleiton.duartee.cliente.escola.gradecurricular.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @GetMapping
    public ResponseEntity<String> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body("Todos");
    }
}
