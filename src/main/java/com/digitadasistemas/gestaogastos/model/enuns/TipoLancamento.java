package com.digitadasistemas.gestaogastos.model.enuns;

public enum TipoLancamento {
	
	RECEITA(1,"RECEITA"),
	DESPESA(2,"DESPESA");

	private int codigo;
	private String descricao;
	
	TipoLancamento(int codigo,String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoLancamento toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(TipoLancamento x : TipoLancamento.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;  
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + codigo);
	}
}
