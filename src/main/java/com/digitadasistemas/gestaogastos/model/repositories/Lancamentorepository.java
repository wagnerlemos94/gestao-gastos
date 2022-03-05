package com.digitadasistemas.gestaogastos.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;

@Repository
public interface Lancamentorepository extends JpaRepository<Lancamento, Long>{

}
