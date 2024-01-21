package com.digitadasistemas.gestaogastos.model.entities;

import javax.persistence.*;

import com.digitadasistemas.gestaogastos.model.enuns.Status;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(name = "NOME", nullable = false,unique = true)
	private String nome;
	@Column(name = "ATIVO")
	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.ATIVO;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Grupo grupo;

	public Categoria (Long id, String nome){
		this.id = id;
		this.nome = nome;
	}

	public Categoria (Long id, String nome, Usuario usuario, Grupo grupo){
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.grupo = grupo;
	}

}
