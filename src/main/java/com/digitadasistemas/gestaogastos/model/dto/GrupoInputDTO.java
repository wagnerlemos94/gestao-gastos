package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.config.GestaoSecurity;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoInputDTO {

    @NotBlank(message = "O campo Grupo é Obrigatório")
    private String descricao;

    public static Grupo to(GrupoInputDTO grupoInputDTO){
        Grupo grupo = new Grupo();
        grupo.setNome(grupoInputDTO.getDescricao().toUpperCase());
        return grupo;
    }

    public static List<Grupo> to(List<GrupoInputDTO> grupoInputDTO, Usuario usuario){
        List<Grupo> grupos = new ArrayList<Grupo>();
        grupoInputDTO.forEach(grupoDTO -> {
            Grupo grupo = GrupoInputDTO.to(grupoDTO);
            grupo.setUsuario(usuario);
            grupos.add(grupo);
        });

        return grupos;
    }
}
