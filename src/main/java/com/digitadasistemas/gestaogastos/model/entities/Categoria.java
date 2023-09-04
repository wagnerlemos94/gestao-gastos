package com.digitadasistemas.gestaogastos.model.entities;

import javax.persistence.*;

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
	@Column(nullable = false,unique = true)
	private String nome;
	private boolean ativo = true;
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
