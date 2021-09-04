package com.cleiton.duartee.cliente.escola.gradecurricular.service;

import com.cleiton.duartee.cliente.escola.gradecurricular.dto.MateriaDTO;
import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.exception.MateriaException;
import com.cleiton.duartee.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Boolean atualizar(MateriaDTO materiaDTO) {
        try{
            this.buscarPorId(materiaDTO.getId());
            MateriaEntity materiaEntityAtualizada = mapper.map(materiaDTO, MateriaEntity.class);
            this.materiaRepository.save(materiaEntityAtualizada);
            return true;
        }catch (MateriaException m){
            throw m;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try{
            this.buscarPorId(id);
            this.materiaRepository.deleteById(id);
            return true;
        }catch (MateriaException m){
            throw m;
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public Boolean cadastrar(MateriaDTO materiaDTO) {
        try{
            MateriaEntity materiaEntity = mapper.map(materiaDTO,MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return true;
        }catch (Exception e){
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @Override
    public List<MateriaEntity> buscarTodos() {
        try{
            return this.materiaRepository.findAll();
        }catch (Exception e){
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
