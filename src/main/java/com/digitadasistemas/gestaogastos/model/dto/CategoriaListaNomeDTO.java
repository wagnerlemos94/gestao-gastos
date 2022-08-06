package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaListaNomeDTO {

    private Long id;
    private String nome;

    public CategoriaListaNomeDTO(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

}
