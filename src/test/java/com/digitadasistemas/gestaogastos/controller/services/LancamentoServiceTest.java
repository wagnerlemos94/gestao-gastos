package com.digitadasistemas.gestaogastos.controller.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.digitadasistemas.gestaogastos.model.enuns.Status;
import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoInput;
import com.digitadasistemas.gestaogastos.model.repositories.LancamentoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	private LancamentoRepository lancamentorepository;
	
	private static final Categoria categoria = new Categoria(1L,"carro");
	private static Set<Role> roles = new HashSet<Role>();

	private static Date data = new Date();
	public static final Usuario usuario = new Usuario(1L, "teste", "teste@email.com","123456", false, false, roles);
	public static final Lancamento lancamento = new Lancamento(null,TipoLancamento.DESPESA, Mes.toEnum("JUNHO"), 2002, "Alinhamento + Balanciamento",60.00,data, Status.PENDENTE,categoria,usuario);
	public static final LancamentoInput lancamentoInput = new LancamentoInput(null,categoria.getId(),"Lancamento edição",TipoLancamento.DESPESA.getCodigo(), 60.00,data, 6, usuario, null);

	@Order(1)
	@Test
	void deveSalvarLancamentoNaBaseDeDados() {
		service.cadastrar(this.lancamentoInput);
		Lancamento lancamento = service.buscar(1L);
		
		assertNotEquals(null, lancamento.getId());
	}
	
	@Order(2)
	@Test
	void deveBuscarUmaLancamento() {
		service.cadastrar(lancamentoInput);
		Lancamento lancamento = service.buscar(1L);
		
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
