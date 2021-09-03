package com.cleiton.duartee.cliente.escola.gradecurricular.service;

import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;

import java.util.List;

public interface IMateriaService {
    public Boolean atualizar (final MateriaEntity materia);
    public Boolean excluir (final Long id);
    public Boolean cadastrar (final MateriaEntity materia);
    public MateriaEntity buscarPorId (final Long id);
    public List<MateriaEntity> buscarTodos ();
}
