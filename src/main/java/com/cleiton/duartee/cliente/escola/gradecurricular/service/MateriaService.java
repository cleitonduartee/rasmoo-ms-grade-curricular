package com.cleiton.duartee.cliente.escola.gradecurricular.service;

import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.exception.MateriaException;
import com.cleiton.duartee.cliente.escola.gradecurricular.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService{

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public Boolean atualizar(MateriaEntity materia) {
        try{
            MateriaEntity materiaEntityAtualizada = this.buscarPorId(materia.getId());
            materiaEntityAtualizada.setHoras(materia.getHoras());
            materiaEntityAtualizada.setCodigo(materia.getCodigo());
            materiaEntityAtualizada.setFrequencia(materia.getFrequencia());
            materiaEntityAtualizada.setNome(materia.getNome());

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
    public Boolean cadastrar(MateriaEntity materia) {
        try{
            this.materiaRepository.save(materia);
            return true;
        }catch (Exception e){
            throw new MateriaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MateriaEntity buscarPorId(Long id) {
        try{
            Optional<MateriaEntity> materiaEntityOptional = this.materiaRepository.findById(id);
            return materiaEntityOptional.orElseThrow(
                    ()-> new MateriaException("Materia não encontrada.", HttpStatus.NOT_FOUND));
        }catch (MateriaException m){
            throw m;
        }catch (Exception e){
            throw new MateriaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MateriaEntity> buscarTodos() {
        try{
            return this.materiaRepository.findAll();
        }catch (Exception e){
            throw new MateriaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
