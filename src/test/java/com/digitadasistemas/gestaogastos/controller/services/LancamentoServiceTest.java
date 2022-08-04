package com.digitadasistemas.gestaogastos.controller.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoInput;
import com.digitadasistemas.gestaogastos.model.repositories.Lancamentorepository;
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
	@Autowired
	private Lancamentorepository lancamentorepository;
	
	private static final Categoria categoria = new Categoria(1L,"carro");
	private static Set<Role> roles = new HashSet<Role>();
	
	public static final Usuario usuario = new Usuario(1L, "teste", "teste@email.com","123456", roles);
	public static final Lancamento lancamento = new Lancamento(null,TipoLancamento.DESPESA,"Alinhamento + Balanciamento",60.00,Mes.JANEIRO,categoria,usuario);
	public static final LancamentoInput lancamentoInput = new LancamentoInput(null,categoria.getId(),"Lancamento edição",Mes.JANEIRO.getCodigo(),TipoLancamento.DESPESA.getCodigo(),60.00,usuario);

	@Order(1)
	@Test
	void deveSalvarLancamentoNaBaseDeDados() {
		Lancamento lancamento = service.cadastrar(this.lancamentoInput);
		
		assertNotEquals(null, lancamento.getId());
	}
	
	@Order(2)
	@Test
	void deveBuscarUmaLancamento() {
		Lancamento lancamento = service.cadastrar(lancamentoInput);
		
		assertNotEquals(null, lancamento);
	}
	
	@Order(3)
	@Test
	void deveListarTodasLancamentos() {
		LancamentoFiltro filtro = new LancamentoFiltro();

		service.cadastrar(this.lancamentoInput);
		service.cadastrar(this.lancamentoInput);
		
//		List<LancamentoConsultaDTO> listaLancamento = service.listar(filtro);
			
//		assertTrue(listaLancamento.size() >= 2);
		
	}
	
	@Order(4)
	@Test
	void deveAtualizarUmaLancamento() {
		service.cadastrar(this.lancamentoInput);
		Long id = lancamentorepository.findAll().get(0).getId();
		LancamentoInput lancamentoInput = this.lancamentoInput;

		service.atualizar(id, lancamentoInput);

		Lancamento LancamentoEditado = service.buscar(id);

		assertEquals("Lancamento edição", LancamentoEditado.getDescricao());
	}
	
}
