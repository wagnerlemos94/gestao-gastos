package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;
import java.util.stream.Collectors;

import com.digitadasistemas.gestaogastos.model.dto.UsuarioConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioInputDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioRepository.findByLogin(login).
				orElseThrow(() -> new ObjetoNaoEncontrado("Usuario ou Senha inválidos: "));
	}
	
	public void cadastrar(UsuarioInputDTO usuarioInputDTO) {
		usuarioInputDTO.setSenha(passwordEncoder.encode(usuarioInputDTO.getSenha()));
		usuarioRepository.save(UsuarioInputDTO.to(usuarioInputDTO));
	}
	
	public UsuarioConsultaDTO buscar(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado("Usuario não encontrado id: " + id));
		return new UsuarioConsultaDTO(usuario);
	}
	
	public List<UsuarioConsultaDTO> listar(){
		return usuarioRepository.findAll().stream()
				.map( usuario -> new UsuarioConsultaDTO(usuario))
				.collect(Collectors.toList());
	}
	
//	public UsuarioInputDTO atualizar(UsuarioInputDTO usuarioInputDTO, Long id) {
//		Usuario usuario = new Usuario();
//		usuario.setId(id);
//		Usuario usuarioAtual = UsuarioConsultaDTO.to(buscar(id));
//		us
//		BeanUtils.copyProperties(lancamento, usuarioAtual);
//		return usuarioRepository.save(usuario);
//	}
	
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario buscarPorLogin(String login) {
		return usuarioRepository.findByLogin(login).
				orElseThrow(() -> new ObjetoNaoEncontrado("Usuario ou Senha inválidos: "));
	}

	
}
