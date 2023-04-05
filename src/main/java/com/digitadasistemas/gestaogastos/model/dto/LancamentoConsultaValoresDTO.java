package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoConsultaValoresDTO {

	private String grupo;
	private String categoria;
	private Long categoriaId;
	private TipoLancamento tipo;
	private Double valor;
	private Mes mes;
}
