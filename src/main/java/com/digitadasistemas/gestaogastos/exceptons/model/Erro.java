package com.digitadasistemas.gestaogastos.exceptons.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class Erro implements Serializable {

    private static final long serialVersionUID = 3746067618307678421L;

    private int statusHttp;
    private String mensagemUsuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateTime;

    public static class  Builder{
        private final Erro erro;

        public Builder() {
            erro = new Erro();
            erro.dateTime = LocalDateTime.now();
        }

        public Builder comStatus(int statusHttp) {
            erro.statusHttp = statusHttp;
            return this;
        }

        public Builder comDetalhe(String detalhe) {
            erro.mensagemUsuario = detalhe;
            return this;
        }

        public Erro build() {
            return erro;
        }

    }

}
