package com.digitadasistemas.gestaogastos.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(nullable = false)
	private TipoLancamento tipo;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private Double valor;	
	@Column(nullable = false)
	private Mes mes;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Categoria categoria;
	
	public void setTipo(int codigo) {
		this.tipo = TipoLancamento.toEnum(codigo);
	}
	
	public void setMes(int codigo) {
		this.mes = Mes.toEnum(codigo);
	}

}
