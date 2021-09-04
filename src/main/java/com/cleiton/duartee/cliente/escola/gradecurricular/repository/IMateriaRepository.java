package com.cleiton.duartee.cliente.escola.gradecurricular.repository;

import com.cleiton.duartee.cliente.escola.gradecurricular.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMateriaRepository extends JpaRepository<MateriaEntity, Long> {
}
