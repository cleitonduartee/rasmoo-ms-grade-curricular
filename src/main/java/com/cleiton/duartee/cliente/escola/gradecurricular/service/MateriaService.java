package com.cleiton.duartee.cliente.escola.gradecurricular.service;

import com.cleiton.duartee.cliente.escola.gradecurricular.controller.MateriaController;
import com.cleiton.duartee.cliente.escola.gradecurricular.dto.MateriaDTO;
import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.exception.MateriaException;
import com.cleiton.duartee.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateriaService{

    private static final String MENSAGEM_ERRO = "Erro interno identificado. Contate o suporte";
    private static final String MATERIA_NAO_ENCONTRADA = "Materia não encontrada.";
    private IMateriaRepository materiaRepository;
    private ModelMapper mapper;


    @Autowired
    public MateriaService(IMateriaRepository iMateriaRepository){
        this.materiaRepository = iMateriaRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public MateriaDTO atualizar(MateriaDTO materiaDTO) {
        try{
            this.buscarPorId(materiaDTO.getId());
            MateriaEntity materiaEntityAtualizada = mapper.map(materiaDTO, MateriaEntity.class);
            materiaDTO = mapper.map(this.materiaRepository.save(materiaEntityAtualizada), MateriaDTO.class);
            return materiaDTO;
        }catch (MateriaException m){
            throw m;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void excluir(Long id) {
        try{
            this.buscarPorId(id);
            this.materiaRepository.deleteById(id);
        }catch (MateriaException m){
            throw m;
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public MateriaDTO cadastrar(MateriaDTO materiaDTO) {
        try{
            MateriaEntity materiaEntity = mapper.map(materiaDTO,MateriaEntity.class);
            return mapper.map(this.materiaRepository.save(materiaEntity), MateriaDTO.class);
        }catch (Exception e){
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut( key = "#id")
    @Override
    public MateriaEntity buscarPorId(Long id) {
        try{
            if(id == null ) throw new MateriaException("Informe o ID da matéria.", HttpStatus.NOT_FOUND);
            Optional<MateriaEntity> materiaEntityOptional = this.materiaRepository.findById(id);
            return materiaEntityOptional.orElseThrow(
                    ()-> new MateriaException(MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND));
        }catch (MateriaException m){
            throw m;
        }catch (Exception e){
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CachePut(unless = "#result.size()<3")
    @Override
    public List<MateriaDTO> buscarTodos() {
        try{
            List<MateriaDTO> listMateria = mapper.map(this.materiaRepository.findAll(), new TypeToken<List<MateriaDTO>>(){}.getType());
            listMateria.forEach(mat -> {
                mat.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).buscarPorId(mat.getId())).withSelfRel());
            });
            return listMateria;
        }catch (Exception e){
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
