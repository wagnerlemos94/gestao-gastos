package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoValoresDTO;
import com.digitadasistemas.gestaogastos.model.repositories.LancamentoSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.Filtro;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import com.digitadasistemas.gestaogastos.model.repositories.Lancamentorepository;

@Service
public class LancamentoService {

	@Autowired
	private Lancamentorepository repository;

	@Transactional
	public Lancamento cadastrar(Lancamento lancamento) {
		lancamento.setId(null);
		return repository.save(lancamento);
	}

	public Lancamento buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Lancamento não encontrado id: " + id));
	}

	public LancamentoValoresDTO valores(Filtro filtro){
		List<Lancamento> lancamentos = repository.findAll(LancamentoSpec.comFiltro(filtro));
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

	public List<LancamentoConsultaDTO> listar(Filtro filtro) {

		List<LancamentoConsultaDTO>lancamentos = repository.findAll(LancamentoSpec.comFiltro(filtro)).stream()
				.map(
				lancamento -> new LancamentoConsultaDTO(lancamento)
				).collect(Collectors.toList());

		return calculoValorTotal(lancamentos);
	}


	@Transactional
	public Lancamento atualizar(Long id, Lancamento lancamento) {
		Lancamento lancamentoAtual = buscar(id);

		BeanUtils.copyProperties(lancamento, lancamentoAtual);

		return lancamento;
	}

	private List<LancamentoConsultaDTO> calculoValorTotal(List<LancamentoConsultaDTO> lancamentos) {
		LancamentoConsultaDTO lancamentosTotal = new LancamentoConsultaDTO();
		lancamentosTotal.setTipo("TOTAL");
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

}
