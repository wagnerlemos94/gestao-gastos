package com.digitadasistemas.gestaogastos.controller.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitadasistemas.gestaogastos.controller.services.LancamentoService;
import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.LancamentoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService service;
	
	@PostMapping
	public ResponseEntity<Lancamento> cadastrar(@RequestBody Lancamento lancamento){
		lancamento = service.cadastrar(lancamento);
		return ResponseEntity.ok().body(lancamento);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id){
		try {			
			Lancamento lancamento = service.buscar(id);
			return ResponseEntity.ok().body(lancamento);
		} catch (ObjetoNaoEncontrado e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<LancamentoConsultaDTO>> listar(){
		List<LancamentoConsultaDTO> lancamentos = service.listar();
		return ResponseEntity.ok().body(lancamentos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lancamento> atualizar(@RequestBody Lancamento lancamento,@PathVariable Long id){
		lancamento = service.atualizar(id, lancamento);
		return ResponseEntity.ok().body(lancamento);
	}

}
