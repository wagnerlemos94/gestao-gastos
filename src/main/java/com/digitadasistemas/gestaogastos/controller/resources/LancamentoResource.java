package com.digitadasistemas.gestaogastos.controller.resources;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoInput;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoValoresDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitadasistemas.gestaogastos.controller.services.LancamentoService;
import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.Filtro;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cadastrar(@Valid @RequestBody LancamentoInput lancamentoInput){
		lancamentoService.cadastrar(lancamentoInput);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id){
		try {			
			Lancamento lancamento = lancamentoService.buscar(id);
			return ResponseEntity.ok().body(lancamento);
		} catch (ObjetoNaoEncontrado e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<LancamentoConsultaDTO>> listar(@PathParam(value = "filtro") Filtro filtro){
		List<LancamentoConsultaDTO> lancamentos = lancamentoService.listar(filtro);
		return ResponseEntity.ok().body(lancamentos);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@Valid @RequestBody LancamentoInput lancamentoInput, @PathVariable Long id){
		lancamentoService.atualizar(id, lancamentoInput);
	}

	@GetMapping("/valores")
	public ResponseEntity<LancamentoValoresDTO> valores(@PathParam(value = "filtro") Filtro filtro){
		return ResponseEntity.ok().body(lancamentoService.valores(filtro));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		lancamentoService.delete(id);
	}

}
