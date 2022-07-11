package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaConsulta {

    private Long id;
    private String nome;
    private String grupo;
    private Long grupoId;

    public CategoriaConsulta(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.grupo = categoria.getGrupo().getNome();
        this.grupoId = categoria.getGrupo().getId();
    }

}
