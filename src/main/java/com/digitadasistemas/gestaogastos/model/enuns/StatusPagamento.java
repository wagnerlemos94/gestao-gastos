package com.digitadasistemas.gestaogastos.model.enuns;

public enum StatusPagamento {

	PENDENTE(1,"PENDENTE"),
	PAGO(2,"PAGO");

	private int codigo;
	private String descricao;

	StatusPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusPagamento toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(StatusPagamento x : StatusPagamento.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status para a Id: " + codigo + " Inválido: ");
	}

	public static StatusPagamento toEnum(String descricao) {
		if(descricao == null) {
			return null;
		}
		for(StatusPagamento x : StatusPagamento.values()) {
			if(descricao.equalsIgnoreCase(x.getDescricao())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status para a Descricão: " + descricao+ " Inválido: ");
	}
}