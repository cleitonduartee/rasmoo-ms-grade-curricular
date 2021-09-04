package com.cleiton.duartee.cliente.escola.gradecurricular.controller;

import com.cleiton.duartee.cliente.escola.gradecurricular.dto.MateriaDTO;
import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.service.IMateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    static final ModelMapper mapper = new ModelMapper();

    @Autowired
    private IMateriaService iMateriaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> buscarTodos(){
        List<MateriaEntity> listMateriaEntity =  this.iMateriaService.buscarTodos();
        List<MateriaDTO> listMateriaDto = listMateriaEntity.stream().map(
                (materiaEntity -> mapper.map(materiaEntity, MateriaDTO.class))
        ).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(listMateriaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> buscarPorId(@PathVariable long id){
        MateriaDTO materiaDTO = mapper.map(this.iMateriaService.buscarPorId(id), MateriaDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(materiaDTO);
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrar(@RequestBody @Valid MateriaDTO materiaDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iMateriaService.cadastrar(materiaDTO));
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
    public ResponseEntity<Boolean> atualizar (@RequestBody @Valid MateriaDTO materiaDTO){
        if(this.iMateriaService.atualizar(materiaDTO).equals(true)) return ResponseEntity.status(HttpStatus.OK).body(true);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
