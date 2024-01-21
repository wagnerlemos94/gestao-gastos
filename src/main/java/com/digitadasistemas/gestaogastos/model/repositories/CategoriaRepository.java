package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.enuns.Status;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>, JpaSpecificationExecutor<Categoria> {

    List<Categoria> findByStatusAndGrupo(Status status, Grupo grupo);
    List<Categoria> findByGrupo(Grupo grupo);
    List<Categoria> findAll(Specification<Categoria> comFiltro);

    Categoria findByNomeAndUsuario(String nome, Usuario usuario);
}
