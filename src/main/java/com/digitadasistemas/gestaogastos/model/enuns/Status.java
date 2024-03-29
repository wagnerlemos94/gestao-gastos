package com.digitadasistemas.gestaogastos.model.enuns;

public enum Status {

	INATIVO(0,"INATIVO","INATIVO NO SISTEMA, DISPONIVEL PARA LISTAGEM MAS NÃO PARA RELACIONAMENTO"),
	ATIVO(1,"ATIVO", "ATIVO NO SISTEMA VAI DISPONIVEL PARA LISTAGEM"),
	EXCLUIDO(2,"EXCLUIDO", "EXCLUIDO PARA O USUÁRIO, NÃO DISPONIVEL PARA LISTAGEM");

	private int codigo;
	private String descricao;
	private String descritivo;

	Status(int codigo, String descricao, String descritivo) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descritivo = descritivo;
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