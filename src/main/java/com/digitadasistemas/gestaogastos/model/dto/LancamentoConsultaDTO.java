package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LancamentoConsultaDTO {
	
	private Long id;
	private String tipo;
	private String descricao;
	private Double valor;	
	private Mes mes;
	@JsonIgnore
	private Long idCategoria;
	private String categoria;
	private String usuario;
	
	public LancamentoConsultaDTO(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.tipo = lancamento.getTipo().getDescricao();
		this.descricao = lancamento.getDescricao();
		this.valor = lancamento.getValor();	
		this.mes = lancamento.getMes();
		this.idCategoria = lancamento.getCategoria().getId();
		this.categoria = lancamento.getCategoria().getNome();
		this.usuario = lancamento.getUsuario().getNome();
	}

	public LancamentoConsultaDTO(LancamentoConsultaDTO lancamento) {
	}
}
