package com.digitadasistemas.gestaogastos.controller.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.entities.Role;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;

@SpringBootTest
public class LancamentoServiceTest {

	@Autowired
	private LancamentoService service;
	
	private static final Categoria categoria = new Categoria(1L,"carro");
	private static Set<Role> roles = new HashSet<Role>();
	
	public static final Usuario usuario = new Usuario(1L, "teste", "teste@email.com","123456", roles);
	
	private Lancamento lancamentoSalvo() {
		return new Lancamento(null,TipoLancamento.DESPESA,"Alinhamento + Balanciamento",60.00,Mes.JANEITO,categoria,usuario);
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
		
		List<LancamentoConsultaDTO> listaLancamento = service.listar(null);
			
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
