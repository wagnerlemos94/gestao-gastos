package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LancamentoEmailDTO {
    private String tipo;
    private String mes;
    private Integer ano;
    private String descricao;
    private Double valor;
    private UsuarioConsultaDTO usuario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date data;
    private String grupo;
    private String categoria;
    private String status;

    public LancamentoEmailDTO(Lancamento lancamento){
        this.tipo = lancamento.getTipo().getDescricao();
        this.mes = lancamento.getMes().getDescricao();
        this.ano = lancamento.getAno();
        this.descricao = lancamento.getDescricao();
        this.valor = lancamento.getValor();
        this.data = lancamento.getData();
        this.grupo = lancamento.getCategoria().getGrupo().getNome();
        this.categoria = lancamento.getCategoria().getNome();
        this.status = lancamento.getStatusPagamento().getDescricao();
        this.usuario = new UsuarioConsultaDTO(lancamento.getUsuario());
    }

}
