package com.digitadasistemas.gestaogastos.controller.resources;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.digitadasistemas.gestaogastos.model.dto.*;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.params.LancamentoUpdateStatusParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitadasistemas.gestaogastos.controller.services.LancamentoService;
import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource{
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cadastrar(@Valid @RequestBody LancamentoInput lancamentoInput){
		lancamentoService.cadastrar(lancamentoInput);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LancamentoDTO buscar(@PathVariable Long id){
		return new LancamentoDTO(lancamentoService.buscar(id));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<LancamentoConsultaDTO> listar(@PathParam(value = "filtro") LancamentoFiltro filtro){
		return lancamentoService.listar(filtro);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "agrupados")
	public List<LancamentoConsultaValoresDTO> listarAgrupado(@PathParam(value = "filtro") LancamentoFiltro filtro) throws ParseException {
		return lancamentoService.listarAgrupado(filtro);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "detalhes")
	public List<LancamentoConsultaDTO> listarLancamentoPorCategoriaETipo(@PathParam(value = "filtro") LancamentoFiltro filtro) {
		return lancamentoService.buscarLancamentoPorCategoriaETipo(filtro);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@Valid @RequestBody LancamentoInput lancamentoInput, @PathVariable Long id){
		lancamentoService.atualizar(id, lancamentoInput);
	}

	@GetMapping("/valores")
	public ResponseEntity<LancamentoValoresDTO> valores(@PathParam(value = "filtro") LancamentoFiltro filtro){
		return ResponseEntity.ok().body(lancamentoService.valores(filtro));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		lancamentoService.delete(id);
	}

	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public int AtualizarStatus(@RequestBody LancamentoUpdateStatusParams params){
		return lancamentoService.atualizarStatus(params);
	}

	@GetMapping("/pendente")
	@ResponseStatus(HttpStatus.OK)
	public List<LancamentoEmailDTO> findAllLancamentosPendentes(){
		return lancamentoService.findAllLancamentosPendentes().stream().map(LancamentoEmailDTO::new).collect(Collectors.toList());
	}

}
