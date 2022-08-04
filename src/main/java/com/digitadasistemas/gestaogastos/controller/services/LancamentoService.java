package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.digitadasistemas.gestaogastos.config.GestaoSecurity;
import com.digitadasistemas.gestaogastos.model.dto.*;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.repositories.CategoriaRepository;
import com.digitadasistemas.gestaogastos.model.repositories.LancamentoSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import com.digitadasistemas.gestaogastos.model.repositories.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentorepository;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private GestaoSecurity gestaoSecurity;

	@Transactional
	public Lancamento cadastrar(LancamentoInput lancamentoInput) {
		Lancamento lancamento = LancamentoInput.to(lancamentoInput);
		lancamento.setUsuario(gestaoSecurity.getUsuario());
		lancamento.setCategoria(categoriaService.buscar(lancamentoInput.getCategoria()));
		return lancamentorepository.save(lancamento);
	}

	public Lancamento buscar(Long id) {
		return lancamentorepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Lancamento não encontrado id: " + id));
	}

	public LancamentoValoresDTO valores(LancamentoFiltro filtro){
		filtro.setUsuario(gestaoSecurity.getUsuario());
		List<Lancamento> lancamentos = lancamentorepository.findAll(LancamentoSpec.comFiltro(filtro));
		return calculoValores(lancamentos);
	}

	private LancamentoValoresDTO calculoValores(List<Lancamento> lancamentos) {
		LancamentoValoresDTO lancamentosTotal = new LancamentoValoresDTO();
		lancamentosTotal.setRecebido(0.0);
		lancamentosTotal.setGasto(0.0);
		lancamentosTotal.setSaldo(0.0);

		lancamentos.forEach(
				lancamento -> {
					if(lancamento.getTipo().equals(TipoLancamento.RECEITA)) {
						lancamentosTotal.setRecebido(lancamentosTotal.getRecebido() + lancamento.getValor() );
					}else if(lancamento.getTipo().equals(TipoLancamento.DESPESA)){
						lancamentosTotal.setGasto(lancamentosTotal.getGasto() + lancamento.getValor());
					}
				});

		lancamentosTotal.setSaldo(lancamentosTotal.getRecebido() - lancamentosTotal.getGasto());

		return lancamentosTotal;
	}

	public List<LancamentoConsultaDTO> listar(LancamentoFiltro filtro) {

		filtro.setUsuario(gestaoSecurity.getUsuario());
		List<LancamentoConsultaDTO>lancamentos = lancamentorepository.findAll(LancamentoSpec.comFiltro(filtro)).stream()
				.map(
				lancamento -> new LancamentoConsultaDTO(lancamento)
				).collect(Collectors.toList());

		return calculoValorTotal(lancamentos);
	}

	public List<LancamentoConsultaValoresDTO> listarAgrupado(LancamentoFiltro filtro) {
		filtro.setUsuario(gestaoSecurity.getUsuario());
		return lancamentorepository.buscarTodos();

	}

	@Transactional
	public void atualizar(Long id, LancamentoInput lancamentoInput) {
		Lancamento lancamentoAtual = buscar(id);

		Categoria categoria = categoriaService.buscar(lancamentoInput.getCategoria());

		Lancamento lancamento = LancamentoInput.to(lancamentoInput);
		lancamento.setCategoria(categoria);
		lancamento.setUsuario(gestaoSecurity.getUsuario());

		BeanUtils.copyProperties(lancamento, lancamentoAtual);
		lancamentorepository.save(lancamento);
	}

	private List<LancamentoConsultaDTO> calculoValorTotal(List<LancamentoConsultaDTO> lancamentos) {
		LancamentoConsultaDTO lancamentosTotal = new LancamentoConsultaDTO();
		lancamentosTotal.setTipo("SALDO");
		lancamentosTotal.setValor(0.0);
		
		lancamentos.forEach(
				lancamento -> {
					if(lancamento.getTipo().equals(TipoLancamento.RECEITA.getDescricao())) {
						lancamentosTotal.setValor(lancamentosTotal.getValor() + lancamento.getValor());
					}else {
						lancamentosTotal.setValor(lancamentosTotal.getValor() - lancamento.getValor());
					}
				});
		lancamentos.add(lancamentosTotal);
		
		return lancamentos;
	}

	@Transactional
	public void delete(Long id){
		lancamentorepository.delete(buscar(id));
	}

}
