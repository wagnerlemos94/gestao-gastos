package com.digitadasistemas.gestaogastos.controller.resources;

import java.util.List;

import com.digitadasistemas.gestaogastos.model.dto.CategoriaConsulta;
import com.digitadasistemas.gestaogastos.model.dto.CategoriaInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitadasistemas.gestaogastos.controller.services.CategoriaService;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cadastrar(@RequestBody @Valid CategoriaInput categoriaInput){
		service.cadastrar(categoriaInput);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable Long id){
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CategoriaConsulta> listar(){
		return service.listar();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@RequestBody @Valid CategoriaInput categoriaCategoriaInput,@PathVariable Long id){
		service.atualizar(categoriaCategoriaInput, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		service.deletar(id);
	}

}
