package com.digitadasistemas.gestaogastos.controller.resources;

import java.util.List;

import com.digitadasistemas.gestaogastos.model.dto.UsuarioConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitadasistemas.gestaogastos.controller.services.UsuarioService;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;

import javax.websocket.server.PathParam;

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
		return new UsuarioConsultaDTO(service.buscar(id));
	}
	
	@GetMapping("/login/{login}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioConsultaDTO buscar(@PathVariable String login){
		return service.buscar(login);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioConsultaDTO> listar(){
		return service.listar();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@RequestBody UsuarioInputDTO usuarioInputDTO,@PathVariable Long id){
		service.atualizar(usuarioInputDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/atualizarStatus")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public int atualizarStatus(@PathParam("ativo") boolean ativo, @PathParam("id") Long id){
		return service.atualizarStatus(ativo,id);
	}

}
