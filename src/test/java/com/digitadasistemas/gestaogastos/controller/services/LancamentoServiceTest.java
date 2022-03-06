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
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;

@SpringBootTest
public class LancamentoServiceTest {

	@Autowired
	private LancamentoService service;
	
	public static final Categoria categoria = new Categoria(1L,"carro");
	
	private Lancamento lancamentoSalvo() {
		return new Lancamento(null,1,"Alinhamento + Balanciamento",60.00,2,categoria);
	}
	
	@Order(1)
	@Test
	void deveSalvarLancamentoNaBaseDeDados() {
		Lancamento lancamento = lancamentoSalvo();
		lancamento = service.cadastrar(lancamento);
		
		assertNotEquals(null, lancamento.getId());
	}
	
	@Order(2)
	@Test
	void deveBuscarUmaLancamento() {
		Lancamento lancamento = lancamentoSalvo();
		lancamento = service.cadastrar(lancamento);
		
		assertNotEquals(null, lancamento);
	}
	
	@Order(3)
	@Test
	void deveListarTodasLancamentos() {
		Lancamento lancamento1 =  lancamentoSalvo();
		Lancamento lancamento2 =  lancamentoSalvo();;
		
		service.cadastrar(lancamento1);
		service.cadastrar(lancamento2);
		
		List<Lancamento> listaLancamento = service.listar();
			
		assertTrue(listaLancamento.size() >= 2);	
		
	}
	
	@Order(4)
	@Test
	void deveAtualizarUmaLancamento() {
		Lancamento lancamento =  lancamentoSalvo();
		Long id = service.cadastrar(lancamento).getId();
		
		lancamento = service.buscar(id);
		
		lancamento.setDescricao("Lancamento edição");
		Lancamento LancamentoEditado = service.atualizar(id, lancamento);
		System.out.println(LancamentoEditado);
		
		assertEquals("Lancamento edição", LancamentoEditado.getDescricao());
	}
	
}
