package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import lombok.Data;

import java.util.List;

@Data
public class GrupoConsultaDTO {

    private Long id;
    private String descricao;

    public GrupoConsultaDTO(Grupo grupo){
        this.id = grupo.getId();
        this.descricao = grupo.getNome();
    }

}
