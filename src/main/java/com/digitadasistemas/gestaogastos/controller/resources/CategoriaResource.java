package com.digitadasistemas.gestaogastos.controller.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitadasistemas.gestaogastos.controller.services.CategoriaService;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@PostMapping
	public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria categoria){
		categoria = service.cadastrar(categoria);
		return ResponseEntity.ok().body(categoria);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable Long id){
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		List<Categoria> categorias = service.listar();
		return ResponseEntity.ok().body(categorias);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria,@PathVariable Long id){
		categoria = service.atualizar(categoria, id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
