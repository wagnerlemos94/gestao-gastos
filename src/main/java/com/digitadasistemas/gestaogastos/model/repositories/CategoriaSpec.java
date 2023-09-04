package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.filtro.CategoriaFiltro;
import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CategoriaSpec {

    public static Specification<Categoria> comFiltro(CategoriaFiltro filtro){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filtro.getUsuario() != null){
                predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));
            }

            if(filtro.isAtivo()){
                predicates.add(builder.equal(root.get("ativo"), filtro.isAtivo()));
            }

            query.orderBy(builder.asc(root.get("grupo")));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
