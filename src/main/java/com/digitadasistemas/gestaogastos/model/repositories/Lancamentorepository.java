package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;

import java.util.List;

@Repository
public interface Lancamentorepository extends JpaRepository<Lancamento, Long>{

    List<Lancamento> findAll(Specification<Lancamento> comFiltro);
}
