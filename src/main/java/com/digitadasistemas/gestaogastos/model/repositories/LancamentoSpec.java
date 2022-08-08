package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import com.digitadasistemas.gestaogastos.util.Ultil;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

public class LancamentoSpec {

    public static Specification<Lancamento> comFiltro(LancamentoFiltro filtro){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filtro.getDataInicio() != null && filtro.getDataFinal() != null){
                try {
                    predicates.add(builder.between(root.get("data"), Ultil.formataData(filtro.getDataInicio()), Ultil.formataData(filtro.getDataFinal())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if(filtro.getCategoria() != null){
                predicates.add(builder.equal(root.get("categoria"), filtro.getCategoria()));
            }


            if(filtro.getTipo() != null){
                predicates.add(builder.equal(root.get("tipo"), TipoLancamento.toEnum(filtro.getTipo())));
            }

            if(filtro.getUsuario() != null){
                predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
