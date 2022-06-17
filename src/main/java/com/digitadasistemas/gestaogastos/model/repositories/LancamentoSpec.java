package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.Filtro;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

public class LancamentoSpec {

    public static Specification<Lancamento> comFiltro(Filtro filtro){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filtro.getMes() != null){
                predicates.add(builder.equal(root.get("mes"), filtro.getMes()));
            }

            if(filtro.getCategoria() != null){
                predicates.add(builder.equal(root.get("categoria_id"), filtro.getCategoria()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
