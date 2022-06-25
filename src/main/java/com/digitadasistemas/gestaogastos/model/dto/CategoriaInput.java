package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaInput {

    private Long id;
    @NotBlank(message = "Campo descrição é Obrigatório")
    private String nome;

    public static Categoria to(CategoriaInput categoriaInput){
        return new Categoria(categoriaInput.getId(),categoriaInput.getNome().toUpperCase());
    }

}
