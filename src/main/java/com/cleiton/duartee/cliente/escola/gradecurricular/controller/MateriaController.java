package com.cleiton.duartee.cliente.escola.gradecurricular.controller;

import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaRepository iMateriaRepository;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(iMateriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> findById(@PathVariable long id){
        MateriaEntity materia = searchById(id);
        if(materia != null) return ResponseEntity.status(HttpStatus.OK).body(materia);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(materia);
    }

    @PostMapping
    public ResponseEntity<MateriaEntity> cadastrar(@RequestBody MateriaEntity materia){
        try {
           iMateriaRepository.save(materia);
            return ResponseEntity.status(HttpStatus.OK).body( iMateriaRepository.save(materia));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        try {
            MateriaEntity materia = searchById(id);
            if(materia != null){
                iMateriaRepository.delete(materia);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping()
    public ResponseEntity<MateriaEntity> upDate (@RequestBody MateriaEntity materia){
        MateriaEntity materiaEntity = searchById(materia.getId());
        upData(materiaEntity, materia);
        return ResponseEntity.status(200).body(iMateriaRepository.save(materia));
    }
    private void upData (MateriaEntity materiaEntity, MateriaEntity materia){

        materiaEntity.setNome(materia.getNome());
        materiaEntity.setCodigo(materia.getCodigo());
        materiaEntity.setFrequencia(materia.getFrequencia());
        materiaEntity.setHoras(materia.getHoras());

    }
    private MateriaEntity searchById(Long id){
        Optional<MateriaEntity>opMateria = iMateriaRepository.findById(id);
        if(opMateria.isPresent()) return opMateria.get();

        return null;
    }
}
