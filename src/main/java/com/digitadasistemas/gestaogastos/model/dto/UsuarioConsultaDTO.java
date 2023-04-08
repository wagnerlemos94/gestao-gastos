package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioConsultaDTO {

    private Long id;
    private String nome;
    private String login;
    private boolean status;
    private boolean root;
    private String email;

    public UsuarioConsultaDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.status = usuario.isAtivo();
        this.root = usuario.isRoot();
        this.email = usuario.getEmail();
    }

    public static Usuario to(UsuarioConsultaDTO usuarioConsultaDTO){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioConsultaDTO.getId());
        usuario.setNome(usuarioConsultaDTO.getNome());
        usuario.setLogin(usuarioConsultaDTO.getLogin());;
        usuario.setAtivo(usuarioConsultaDTO.isStatus());
        usuario.setRoot(usuarioConsultaDTO.isRoot());
        usuario.setEmail(usuarioConsultaDTO.getEmail());
        return usuario;
    }

}
