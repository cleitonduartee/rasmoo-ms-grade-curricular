package com.cleiton.duartee.cliente.escola.gradecurricular.controller;

import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaService iMateriaService;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> buscarPorId(@PathVariable long id){
       MateriaEntity materiaEntity = this.iMateriaService.buscarPorId(id);
       if(materiaEntity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

       return ResponseEntity.status(HttpStatus.OK).body(materiaEntity);
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrar(@RequestBody MateriaEntity materia){
        try {
         this.iMateriaService.cadastrar(materia);
         return ResponseEntity.status(HttpStatus.OK).body(true);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletar (@PathVariable Long id){
        if(this.iMateriaService.excluir(id).equals(true)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping()
    public ResponseEntity<Boolean> upDate (@RequestBody MateriaEntity materia){
        if(this.iMateriaService.atualizar(materia).equals(true)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
