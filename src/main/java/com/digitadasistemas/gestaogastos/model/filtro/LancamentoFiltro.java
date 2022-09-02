package com.digitadasistemas.gestaogastos.model.filtro;

import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoFiltro {

	private String tipo;
	private String dataInicio;
	private String dataFinal;
	private Integer categoria;
	private Usuario usuario;
	private Integer mes;

}
