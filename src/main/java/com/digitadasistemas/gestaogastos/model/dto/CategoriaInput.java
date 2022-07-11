package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaInput {

    private Long id;
    @NotBlank(message = "Campo descrição é Obrigatório")
    private String nome;
    @NotNull(message = "Campo Grupo é Obrigatório")
    private Long grupo;

    public static Categoria to(CategoriaInput categoriaInput){
        Categoria categoria = new Categoria();
        Grupo grupo = new Grupo();
        grupo.setId(categoriaInput.getGrupo());
        categoria.setId(categoriaInput.getId());
        categoria.setNome(categoriaInput.getNome().toUpperCase());
        categoria.setGrupo(grupo);
        return categoria;
    }

}
