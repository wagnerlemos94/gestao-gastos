package com.digitadasistemas.gestaogastos.model.entities;

import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.Status;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
	@Enumerated(EnumType.STRING)
	private Mes mes;
	@Column(nullable = false)
	private Integer ano;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private Double valor;
	@Column(nullable = false)
	private Date data;
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDENTE;
	
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
