package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioConsultaDTO {

    private final Long id;
    private final String nome;
    private final String login;
    private final String status;
    private final boolean root;

    public UsuarioConsultaDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.status = usuario.isAtivo() ? "Ativo" : "Inativo";
        this.root = usuario.isRoot();
    }

    public static Usuario to(UsuarioConsultaDTO usuarioConsultaDTO){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioConsultaDTO.getId());
        usuario.setNome(usuarioConsultaDTO.getNome());
        usuario.setLogin(usuarioConsultaDTO.getLogin());;
        usuario.setAtivo(usuarioConsultaDTO.getStatus() == "Ativo" ? true : false);
        usuario.setRoot(usuarioConsultaDTO.isRoot());
        return usuario;
    }

}
