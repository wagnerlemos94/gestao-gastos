package com.digitadasistemas.gestaogastos.model;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LancamentoConsultaDTO {
	
	private Long id;
	private TipoLancamento tipo;
	private String descricao;
	private Double valor;	
	private Mes mes;
	private String categoria;
	private String usuario;
	
	public LancamentoConsultaDTO(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.tipo = lancamento.getTipo();
		this.descricao = lancamento.getDescricao();
		this.valor = lancamento.getValor();	
		this.mes = lancamento.getMes();
		this.categoria = lancamento.getCategoria().getNome();
		this.usuario = lancamento.getUsuario().getNome();
	}

}
