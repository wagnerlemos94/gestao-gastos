package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class LancamentoConsultaDTO {
	
	private Long id;
	private String tipo;
	private String descricao;
	private Double valor;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date data;
	private Long idCategoria;
	private String categoria;
	private String grupo;
	private String usuario;
	
	public LancamentoConsultaDTO(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.tipo = lancamento.getTipo().getDescricao();
		this.descricao = lancamento.getDescricao();
		this.valor = lancamento.getValor();
		this.data = lancamento.getData();
		this.idCategoria = lancamento.getCategoria().getId();
		this.categoria = lancamento.getCategoria().getNome();
		this.grupo = lancamento.getCategoria().getGrupo().getNome();
		this.usuario = lancamento.getUsuario().getNome();
	}

}
