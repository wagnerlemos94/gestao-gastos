package com.digitadasistemas.gestaogastos.model.entities;

import javax.persistence.*;

import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

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
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoLancamento tipo;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private Double valor;
	@Column(nullable = false)
	private Date data;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	public void setTipo(int codigo) {
		this.tipo = TipoLancamento.toEnum(codigo);
	}

}
