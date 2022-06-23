package com.digitadasistemas.gestaogastos.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    @Query(value = "SELECT * FROM categoria c WHERE UPPER(c.nome) = UPPER (:nome)", nativeQuery = true)
    Optional<Categoria> findByNome(String nome);

}
