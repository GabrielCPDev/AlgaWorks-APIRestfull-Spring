package com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.Comentario;

@Repository
public interface ComentarioRepositoy extends JpaRepository<Comentario, Long> {

}
