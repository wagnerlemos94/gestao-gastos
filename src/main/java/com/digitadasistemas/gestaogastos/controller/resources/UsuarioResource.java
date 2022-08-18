package com.digitadasistemas.gestaogastos.controller.resources;

import java.util.List;

import com.digitadasistemas.gestaogastos.model.dto.UsuarioConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitadasistemas.gestaogastos.controller.services.UsuarioService;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrar(@RequestBody UsuarioInputDTO usuarioInputDTO){
		service.cadastrar(usuarioInputDTO);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioConsultaDTO buscar(@PathVariable Long id){
		return service.buscar(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email){
		Usuario usuario = service.buscarPorLogin(email);
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioConsultaDTO> listar(){
		return service.listar();
	}
	
//	@PutMapping("/{id}")
//	public UsuarioConsultaDTO atualizar(@RequestBody UsuarioInputDTO usuarioInputDTO,@PathVariable Long id){
//		return service.atualizar(usuarioInputDTO, id);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
