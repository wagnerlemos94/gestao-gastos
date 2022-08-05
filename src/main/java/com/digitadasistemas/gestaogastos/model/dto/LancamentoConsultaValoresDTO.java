package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import lombok.*;

import javax.persistence.SqlResultSetMapping;

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

}
