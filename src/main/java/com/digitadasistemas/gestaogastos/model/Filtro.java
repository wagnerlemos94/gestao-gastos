package com.digitadasistemas.gestaogastos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filtro {
	
	private Integer mes;
	private Long categoria;

}
