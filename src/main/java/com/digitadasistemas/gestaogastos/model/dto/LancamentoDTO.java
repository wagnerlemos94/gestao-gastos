package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LancamentoDTO {

    private Long id;
    private String tipo;
    private String mes;
    private Integer ano;
    private String descricao;
    private Double valor;
    private Date data;
    private String categoria;
    private String status;
    private UsuarioConsultaDTO usuario;

    public LancamentoDTO(Lancamento lancamento){
        this.id = lancamento.getId();
        this.tipo = lancamento.getTipo().getDescricao();
        this.mes = lancamento.getMes().getDescricao();
        this.ano = lancamento.getAno();
        this.descricao = lancamento.getDescricao();
        this.valor = lancamento.getValor();
        this.data = lancamento.getData();
        this.categoria = lancamento.getCategoria().getNome();
        this.usuario = new UsuarioConsultaDTO(lancamento.getUsuario());
        this.status = lancamento.getStatusPagamento().getDescricao();
    }

}
