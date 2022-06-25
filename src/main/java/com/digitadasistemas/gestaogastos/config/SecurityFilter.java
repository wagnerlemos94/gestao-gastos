package com.digitadasistemas.gestaogastos.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityFilter implements Filter {

    public static final String USUARIO_LOGADO = "usuarioLogado";

    private static final String BEARER = "Bearer ";
    private static final String PATH_OAUTH_TOKEN = "/oauth/token";
    private static final String AUTHORIZATION = "Authorization";

//    @Value("gestao.jwt.secret")
    private String secret = "gestaoGasto";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(AUTHORIZATION);

        if ((token != null && token != "") && !req.getServletPath().contains(PATH_OAUTH_TOKEN)) {
            token = token.replace(BEARER, "");
            Claims claims = decodeJWT(token);
            if (Objects.nonNull(claims)) {
                UsuarioLogado usuarioLogado = new UsuarioLogado();
                usuarioLogado.setToken(token);
                usuarioLogado.setJti((String) claims.get("jti"));
                usuarioLogado.setIdUsuario(claims.get("usuario", Long.class));
                usuarioLogado.setUsuario((String) claims.getOrDefault("user_name", null));
                req.setAttribute(USUARIO_LOGADO, usuarioLogado);
            }
        }
        chain.doFilter(req, response);
    }

    public Claims decodeJWT(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            System.out.println("Token inválido.");
            return null;
        }
    }
}
