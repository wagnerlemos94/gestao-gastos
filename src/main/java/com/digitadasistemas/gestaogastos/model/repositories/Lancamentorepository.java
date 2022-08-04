package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaValoresDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;

import java.util.List;

@Repository
public interface Lancamentorepository extends JpaRepository<Lancamento, Long>{

    List<Lancamento> findAll(Specification<Lancamento> comFiltro);

//    @Query(value = "select c.nome as grupo, c.nome as categoria, l.tipo, l.categoria_id, sum(l.valor) as valor from lancamento l" +
//            " join categoria c on c.id = l.categoria_id" +
//            " join grupo g on g.id = c.grupo_id" +
//            " group by g.nome, c.nome, l.tipo, l.categoria_id" +
//            " order by l.tipo asc", nativeQuery = true)
//    List<LancamentoConsultaValoresDTO> buscarTodos(Specification<Lancamento> comFiltro);

}
