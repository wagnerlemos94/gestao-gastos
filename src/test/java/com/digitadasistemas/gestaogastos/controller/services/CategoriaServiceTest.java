package com.digitadasistemas.gestaogastos.controller.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;

@SpringBootTest
public class CategoriaServiceTest {

	@Autowired
	private CategoriaService service;
	
	@Order(1)
	@Test
	void deveSalvarCategoriaNaBaseDeDados() {
		Categoria categoria =  new Categoria(null, "Categoria de teste");
		categoria = service.cadastrar(categoria);
		
		assertNotEquals(null, categoria.getId());
	}
	
	@Order(2)
	@Test
	void deveBuscarUmaCategoria() {
		Categoria categoria =  new Categoria(null, "teste categoria");
		categoria = service.cadastrar(categoria);
		
		assertNotEquals(null, categoria);
	}
	
	@Order(3)
	@Test
	void deveListarTodasCategorias() {
		Categoria categoria1 =  new Categoria(null, "categoria1");
		Categoria categoria2 =  new Categoria(null, "categoria2");
		
		service.cadastrar(categoria1);
		service.cadastrar(categoria2);
		
		List<Categoria> listaCategoria = service.listar();
		
		assertTrue(listaCategoria.size() > 2);	
		
	}
	
	@Order(4)
	@Test
	void deveAtualizarUmaCategoria() {
		Categoria categoria =  new Categoria(null, "categoria");
		Long id = service.cadastrar(categoria).getId();
		
		categoria = service.buscar(id);
		
		categoria.setNome("categoria edição");
		Categoria categoriaEditada = service.atualizar(categoria, id);
		
		assertEquals("categoria edição", categoriaEditada.getNome());
	}
	
}
