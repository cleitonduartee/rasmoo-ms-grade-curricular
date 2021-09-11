package com.cleiton.duartee.cliente.escola.gradecurricular.service;

import com.cleiton.duartee.cliente.escola.gradecurricular.dto.MateriaDTO;
import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;

import java.util.List;

public interface IMateriaService {
    public MateriaDTO atualizar (final MateriaDTO materiaDTO);
    public void excluir (final Long id);
    public MateriaDTO cadastrar (final MateriaDTO materiaDTO);
    public MateriaEntity buscarPorId (final Long id);
    public List<MateriaDTO> buscarTodos ();
}
