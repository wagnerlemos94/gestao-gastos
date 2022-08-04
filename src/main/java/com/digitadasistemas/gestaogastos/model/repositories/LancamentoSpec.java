package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

public class LancamentoSpec {

    public static Specification<Lancamento> comFiltro(LancamentoFiltro filtro){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filtro.getMes() != null){
                predicates.add(builder.equal(root.get("mes"), filtro.getMes()));
            }

            if(filtro.getCategoria() != null){
                predicates.add(builder.equal(root.get("categoria_id"), filtro.getCategoria()));
            }

            if(filtro.getUsuario() != null){
                predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));
            }

//            Join<Lancamento, Categoria> categoria = root.join("categoria");
//            Join<Lancamento, Grupo> grupo = categoria.join("grupo");

//            builder.createQuery().select(grupo.get("id")).from(Lancamento.class);

//            query.select(grupo.get("id"))
//                    .groupBy(Arrays.asList(root.get("id"), grupo.get("nome"), categoria.get("nome"),
//                            root.get("tipo"), categoria.get("id")));

//            query.groupBy(Arrays.asList(grupo.get("nome")));
//            query.orderBy(builder.asc(root.get("tipo")));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
