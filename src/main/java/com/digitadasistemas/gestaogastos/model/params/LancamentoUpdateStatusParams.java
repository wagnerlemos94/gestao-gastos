package com.digitadasistemas.gestaogastos.model.params;

import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoUpdateStatusParams {

	private int categoria;
	private int usuario;
	private Mes mes;
	private int ano;
	private int status;

	public String getStatus(){
		return StatusPagamento.toEnum(status).getDescricao();
	}

}
