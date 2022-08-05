package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaValoresDTO;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;

import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

    List<Lancamento> findAll(Specification<Lancamento> comFiltro);

    List<Lancamento> findAllByCategoria(Categoria categoria);

    @Query(value = "select new com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaValoresDTO(g.nome , c.nome, c.id, l.tipo, sum(l.valor)) " +
            "from Lancamento l" +
            " join Categoria c on c.id = l.categoria.id" +
            " join Grupo g on g.id = c.grupo.id" +
//            " where l.usuario.id = :usuarioId" +
            " group by g.nome, c.nome, l.tipo, c.id" +
            " order by l.tipo asc")
    List<LancamentoConsultaValoresDTO> buscarTodos();

}
