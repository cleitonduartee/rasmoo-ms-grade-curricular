package com.cleiton.duartee.cliente.escola.gradecurricular.service;

import com.cleiton.duartee.cliente.escola.gradecurricular.dto.MateriaDTO;
import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;

import java.util.List;

public interface IMateriaService {
    public Boolean atualizar (final MateriaDTO materiaDTO);
    public Boolean excluir (final Long id);
    public Boolean cadastrar (final MateriaDTO materiaDTO);
    public MateriaEntity buscarPorId (final Long id);
    public List<MateriaDTO> buscarTodos ();
}
