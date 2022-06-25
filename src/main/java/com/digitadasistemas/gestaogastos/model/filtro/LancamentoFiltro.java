package com.digitadasistemas.gestaogastos.model.filtro;

import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoFiltro {
	
	private Integer mes;
	private Integer categoria;
	private Usuario usuario;

}
