package com.digitadasistemas.gestaogastos.model.enuns;

public enum Status {

	PENDENTE(1,"PENDENTE"),
	PAGO(2,"PAGO");

	private int codigo;
	private String descricao;

	Status(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(Status x : Status.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status para a Id: " + codigo + " Inválido: ");
	}

	public static Status toEnum(String descricao) {
		if(descricao == null) {
			return null;
		}
		for(Status x : Status.values()) {
			if(descricao.equalsIgnoreCase(x.getDescricao())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status para a Descricão: " + descricao+ " Inválido: ");
	}
}