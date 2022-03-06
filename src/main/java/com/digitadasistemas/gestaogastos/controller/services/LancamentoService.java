package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
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
	
	public List<Lancamento> listar(){
		return repository.findAll();
	}
	
	@Transactional
	public Lancamento atualizar(Long id, Lancamento lancamento) {
		Lancamento lancamentoAtual = buscar(id);
		
		BeanUtils.copyProperties(lancamento, lancamentoAtual);
		
		return lancamento;		
	}

}
