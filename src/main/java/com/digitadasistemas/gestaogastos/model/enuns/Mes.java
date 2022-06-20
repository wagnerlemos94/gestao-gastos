package com.digitadasistemas.gestaogastos.model.enuns;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Mes {

	ORDINAl(0,"ordinal"),
	JANEITO(1,"JANEITO"),
	FEVEREIRO(2,"FEVEREIRO"),
	MARÇO(3,"MARÇO"),
	ABRIL(4,"ABRIL"),
	MAIO(5,"MAIO"),
	JUNHO(6,"JUNHO"),
	JULHO(7,"JULHO"),
	AGOSTO(8,"AGOSTO"),
	SETEMBRO(9,"SETEMBRO"),
	OUTUBRO(10,"OUTUBRO"),
	NOVEMBRO(11,"NOVEMBRO"),
	DEZEMBRO(12,"DEZEMBRO");

	private int codigo;
	private String descricao;
	
	Mes(int codigo,String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static Mes toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(Mes x : Mes.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;  
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + codigo);
	}
}
