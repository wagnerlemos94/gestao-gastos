package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaValoresDTO;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import com.digitadasistemas.gestaogastos.model.params.LancamentoUpdateStatusParams;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

    List<Lancamento> findAll(Specification<Lancamento> comFiltro);

    @Query(value = "select new com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaValoresDTO(g.nome , c.nome, c.id, l.tipo, sum(l.valor), l.mes) " +
            " from Lancamento l" +
            " join Categoria c on c.id = l.categoria.id" +
            " join Grupo g on g.id = c.grupo.id" +
            " where l.data between :dataInicio" +
            " and :dataFinal" +
            " and l.usuario = :usuario" +
            " group by g.nome, c.nome, l.tipo, c.id" +
            " order by l.tipo asc")
    List<LancamentoConsultaValoresDTO> buscarTodos(Usuario usuario, Date dataInicio, Date dataFinal);

    @Query(value = "select new com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaValoresDTO(g.nome , c.nome, c.id, l.tipo, sum(l.valor), l.mes) " +
            " from Lancamento l" +
            " join Categoria c on c.id = l.categoria.id" +
            " join Grupo g on g.id = c.grupo.id" +
            " where l.mes = :mes" +
            " and l.ano = :ano" +
            " and l.usuario = :usuario" +
            " group by g.nome, c.nome, l.tipo, c.id" +
            " order by l.tipo asc")
    List<LancamentoConsultaValoresDTO> buscarTodos(Usuario usuario,Mes mes, Integer ano);

    @Modifying
    @Transactional
    @Query(value = "update lancamento l set status = :#{#params.status} " +
            " where l.mes = :#{#params.mes.descricao} " +
            " and l.ano = :#{#params.ano} " +
            " and l.categoria_id = :#{#params.categoria} " +
            " and l.usuario_id = :#{#params.usuario}", nativeQuery = true)
    int atualizarStatus(LancamentoUpdateStatusParams params);

}
