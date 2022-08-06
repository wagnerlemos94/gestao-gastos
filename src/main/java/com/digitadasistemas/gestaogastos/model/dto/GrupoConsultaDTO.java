package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GrupoConsultaDTO {

    private Long id;
    private String nome;
    private List<CategoriaListaNomeDTO> categorias;

    public GrupoConsultaDTO(Grupo grupo){
        this.id = grupo.getId();
        this.nome = grupo.getNome();
    }

}
