package com.cleiton.duartee.cliente.escola.gradecurricular.controller;

import com.cleiton.duartee.cliente.escola.gradecurricular.dto.MateriaDTO;
import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.model.Response;
import com.cleiton.duartee.cliente.escola.gradecurricular.service.IMateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.MvcLink;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private static final String DELETE = "DELETE";

    private static final String UPDATE = "UPDATE";

    static final ModelMapper mapper = new ModelMapper();

    @Autowired
    private IMateriaService iMateriaService;

    @GetMapping
    public ResponseEntity<Response<List<MateriaDTO>>> buscarTodos(){
        List<MateriaDTO> listMateriaDto = this.iMateriaService.buscarTodos();
        Response<List<MateriaDTO>> response = new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setData(listMateriaDto);
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).buscarTodos()).withSelfRel());
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MateriaDTO>> buscarPorId(@PathVariable long id){
        Response<MateriaDTO> response = new Response<>();
        response.setData(mapper.map(this.iMateriaService.buscarPorId(id), MateriaDTO.class));
        response.setStatus(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).buscarPorId(id)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).deletar(id)).withRel(DELETE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizar(response.getData())).withRel(UPDATE));
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
