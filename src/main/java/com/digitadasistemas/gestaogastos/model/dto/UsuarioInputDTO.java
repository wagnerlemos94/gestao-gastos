package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioInputDTO {

    private String nome;
    private String login;
    private String senha;
    private String email;

    public static Usuario to(UsuarioInputDTO usuarioInputDTO){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioInputDTO.getNome());
        usuario.setLogin(usuarioInputDTO.getLogin());
        usuario.setSenha(usuarioInputDTO.getSenha());
        usuario.setEmail(usuarioInputDTO.getEmail());
        return usuario;
    }

}
