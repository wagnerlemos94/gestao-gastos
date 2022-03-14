package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.Filtro;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
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

	public List<LancamentoConsultaDTO> listar(Filtro filtro) {

		List<LancamentoConsultaDTO>lancamentos = repository.findAll().stream()
				.map(
				lancamento -> new LancamentoConsultaDTO(lancamento)
				).collect(Collectors.toList());

		lancamentos = filtro(lancamentos, filtro);
		
		return lancamentos;
	}


	@Transactional
	public Lancamento atualizar(Long id, Lancamento lancamento) {
		Lancamento lancamentoAtual = buscar(id);

		BeanUtils.copyProperties(lancamento, lancamentoAtual);

		return lancamento;
	}
	
	private List<LancamentoConsultaDTO> filtro(List<LancamentoConsultaDTO> lancamentos, Filtro filtro) {
		if(filtro.getMes() != null) {
			lancamentos = lancamentos.stream().filter(lancamento -> lancamento.getMes().getCodigo() == filtro.getMes())
					.collect(Collectors.toList());
		}
		if((filtro.getCategoria() != null)) {
			lancamentos = lancamentos.stream().filter(lancamento -> lancamento.getIdCategoria() == filtro.getCategoria())
					.collect(Collectors.toList());
		}
		return lancamentos;
		
	}

}
