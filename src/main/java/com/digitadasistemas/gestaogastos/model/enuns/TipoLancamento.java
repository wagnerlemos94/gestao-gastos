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

	public static TipoLancamento toEnum(String descricao) {
		if(descricao == null) {
			return null;
		}
		for(TipoLancamento x : TipoLancamento.values()) {
			if(descricao.equalsIgnoreCase(x.getDescricao())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Descricão Inválido: " + descricao);
	}
}
