package com.digitadasistemas.gestaogastos.controller.services.exception;

import java.util.NoSuchElementException;

public class ObjetoJaExiste extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjetoJaExiste(String msg) {
		super(msg);
	}


}
