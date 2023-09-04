package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    List<Categoria> findByAtivoAndGrupo(boolean ativo, Grupo grupo);
    List<Categoria> findByGrupo(Grupo grupo);
    List<Categoria> findAll(Specification<Categoria> comFiltro);

}
