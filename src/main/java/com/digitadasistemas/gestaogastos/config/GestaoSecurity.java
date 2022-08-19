package com.digitadasistemas.gestaogastos.config;

import com.digitadasistemas.gestaogastos.controller.services.UsuarioService;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioConsultaDTO;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class GestaoSecurity {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioLogado getUsuarioLogado() {
        return (UsuarioLogado) getHttpServletRequest().getAttribute(SecurityFilter.USUARIO_LOGADO);
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public Usuario getUsuario(){
        return UsuarioConsultaDTO.to(usuarioService.buscar(getUsuarioLogado().getUsuario()));
    }
}
