package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GrupoInputDTO {

    @NotBlank(message = "O campo Grupo é Obrigatório")
    private String descricao;

    public static Grupo to(GrupoInputDTO grupoInputDTO){
        Grupo grupo = new Grupo();
        grupo.setNome(grupoInputDTO.getDescricao().toUpperCase());
        return grupo;
    }
}
