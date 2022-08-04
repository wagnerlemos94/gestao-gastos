package com.digitadasistemas.gestaogastos.controller.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.digitadasistemas.gestaogastos.model.dto.CategoriaConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.CategoriaInput;
import com.digitadasistemas.gestaogastos.model.repositories.UsuarioRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;

@SpringBootTest
public class CategoriaServiceTest {

	@Autowired
	private CategoriaService service;
	@Autowired
	private UsuarioRepository usuarioRepository;

	private static final CategoriaInput categoriaInput = new CategoriaInput(null,"teste",1L);
	
	@Order(1)
	@Test
	void deveSalvarCategoriaNaBaseDeDados() {
		Categoria categoria = service.cadastrar(categoriaInput);
		
		assertNotEquals(null, categoria.getId());
	}
	
	@Order(2)
	@Test
	void deveBuscarUmaCategoria() {
		Categoria categoria = service.cadastrar(categoriaInput);
		
		assertNotEquals(null, categoria);
	}
	
	@Order(3)
	@Test
	void deveListarTodasCategorias() {
		CategoriaInput categoria1 =  categoriaInput;
		CategoriaInput categoria2 =  new CategoriaInput(null,"ttt", 2L);
		
		service.cadastrar(categoria1);
		service.cadastrar(categoria2);
		
		List<CategoriaConsultaDTO> listaCategoria = service.listar();
		
		assertTrue(listaCategoria.size() > 2);	
		
	}
	
	@Order(4)
	@Test
	void deveAtualizarUmaCategoria() {
		CategoriaInput categoriaInput =  new CategoriaInput(null, "aaa", 1L);
		Long id = service.cadastrar(categoriaInput).getId();

		Categoria categoriaEditada = service.atualizar(categoriaInput, id);
		
		assertEquals("categoria edição", categoriaEditada.getNome());
	}
	
}
