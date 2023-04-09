package com.digitadasistemas.gestaogastos.controller.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.digitadasistemas.gestaogastos.model.dto.CategoriaInput;
import com.digitadasistemas.gestaogastos.model.dto.GrupoInputDTO;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioInputDTO;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import lombok.Data;
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
@Data
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private CategoriaService categoriaService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioRepository.findByLogin(login).
				orElseThrow(() -> new ObjetoNaoEncontrado("Usuario ou Senha inválidos: "));
	}
	
	public void cadastrar(UsuarioInputDTO usuarioInputDTO) {
		usuarioInputDTO.setSenha(passwordEncoder.encode(usuarioInputDTO.getLogin()));
		Usuario usuario = usuarioRepository.save(UsuarioInputDTO.to(usuarioInputDTO));
		usuarioInputDTO.setSenha(usuarioInputDTO.getLogin());
		cadastroInicial(usuario);
		emailService.sendMail(usuarioInputDTO, usuarioInputDTO.getEmail(), "Novo Usuário");
	}

	private void cadastroInicial(Usuario usuario){
		Grupo grupo = cadastrarGrupoInicial(usuario);
		cadastroCategoriaInicial(usuario,grupo);
	}

	private Grupo cadastrarGrupoInicial(Usuario usuario){
		List<Grupo> grupos = grupoService.cadastro(Arrays.asList(
				new GrupoInputDTO("GERAL"),
				new GrupoInputDTO("CARTÃO DE CRÉDITO")),usuario);
		return grupos.stream().filter(grupo -> grupo.getNome() == "GERAL").collect(Collectors.toList()).stream().findFirst().get();
	}

	private void cadastroCategoriaInicial(Usuario usuario, Grupo grupo){
		categoriaService.cadastrar(new Categoria(null,"DIVERSOS",usuario,grupo));
	}
	
	public Usuario buscar(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado("Usuario não encontrado id: " + id));
	}

	public UsuarioConsultaDTO buscar(String login) {
		Usuario usuario = usuarioRepository.findByLogin(login).
				orElseThrow(() -> new ObjetoNaoEncontrado("Usuario ou Senha inválidos: "));
		return new UsuarioConsultaDTO(usuario);
	}
	
	public List<UsuarioConsultaDTO> listar(){
		return usuarioRepository.findAll().stream()
				.map( usuario -> new UsuarioConsultaDTO(usuario))
				.collect(Collectors.toList());
	}
	
	public void atualizar(UsuarioInputDTO usuarioInputDTO, Long id) {
		Usuario usuario = UsuarioInputDTO.to(usuarioInputDTO);
		usuario.setId(id);
		usuario.setSenha(passwordEncoder.encode(usuarioInputDTO.getSenha()));
		Usuario usuarioAtual = buscar(id);
		usuario.setAtivo(usuarioAtual.isAtivo());
		usuario.setRoot(usuarioAtual.isRoot());
		BeanUtils.copyProperties(usuario, usuarioAtual);
		usuarioRepository.save(usuario);
	}
	
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}

	public int atualizarStatus(boolean ativo,Long id) {
		return usuarioRepository.atualizarStatus(ativo,id);
	}
	
}
