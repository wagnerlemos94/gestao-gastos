package com.digitadasistemas.gestaogastos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
public class UsuarioLogado implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jti;

    private String usuario;

    private Long idUsuario;

    private String token;
}
