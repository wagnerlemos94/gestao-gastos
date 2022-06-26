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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitadasistemas.gestaogastos.controller.services.UsuarioService;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		usuario = service.cadastrar(usuario);
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id){
		Usuario usuario = service.buscar(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email){
		Usuario usuario = service.buscarPorLogin(email);
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> Usuarios = service.listar();
		return ResponseEntity.ok().body(Usuarios);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario,@PathVariable Long id){
		usuario = service.atualizar(usuario, id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
