package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(email).
				orElseThrow(() -> new ObjetoNaoEncontrado("Usuario ou Senha inválidos: "));
	}
	
	public Usuario cadastrar(Usuario usuario) {
		usuario.setId(null);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario buscar(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Usuario não encontrado id: " + id));
	}
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Usuario atualizar(Usuario usuario, Long id) {
		usuario.setId(id);
		return usuarioRepository.save(usuario);
	}
	
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email).
				orElseThrow(() -> new ObjetoNaoEncontrado("Usuario ou Senha inválidos: "));
	}

	
}
