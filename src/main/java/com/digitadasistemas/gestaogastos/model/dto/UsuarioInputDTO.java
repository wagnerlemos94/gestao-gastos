package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioInputDTO {

    private final String nome;
    private final String login;
    private String senha;

    public static Usuario to(UsuarioInputDTO usuarioInputDTO){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioInputDTO.getNome());
        usuario.setLogin(usuarioInputDTO.getLogin());
        usuario.setSenha(usuarioInputDTO.getSenha());
        return usuario;
    }

}
