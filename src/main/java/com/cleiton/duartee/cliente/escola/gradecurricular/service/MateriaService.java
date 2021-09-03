package com.cleiton.duartee.cliente.escola.gradecurricular.service;

import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cleiton.duartee.cliente.escola.gradecurricular.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService{

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public Boolean atualizar(MateriaEntity materia) {
       try{
        MateriaEntity materiaEntityAtualizada = this.materiaRepository.findById(materia.getId()).get();

        materiaEntityAtualizada.setHoras(materia.getHoras());
        materiaEntityAtualizada.setCodigo(materia.getCodigo());
        materiaEntityAtualizada.setFrequencia(materia.getFrequencia());
        materiaEntityAtualizada.setNome(materia.getNome());

        this.materiaRepository.save(materiaEntityAtualizada);

        return true;

       }catch (Exception e){
           return false;
       }
    }

    @Override
    public Boolean excluir(Long id) {
        try{
            this.materiaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public Boolean cadastrar(MateriaEntity materia) {
       try{
           this.materiaRepository.save(materia);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public MateriaEntity buscarPorId(Long id) {
       try{
           return this.materiaRepository.findById(id).get();
       }catch (Exception e){
           return null;
       }

    }

    @Override
    public List<MateriaEntity> buscarTodos() {
        try{
            return this.materiaRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
