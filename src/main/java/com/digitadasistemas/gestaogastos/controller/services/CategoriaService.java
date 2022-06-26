package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;
import java.util.stream.Collectors;

import com.digitadasistemas.gestaogastos.config.GestaoSecurity;
import com.digitadasistemas.gestaogastos.model.dto.CategoriaConsulta;
import com.digitadasistemas.gestaogastos.model.dto.CategoriaInput;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoInput;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.filtro.CategoriaFiltro;
import com.digitadasistemas.gestaogastos.model.repositories.CategoriaSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private GestaoSecurity gestaoSecurity;
	
	public Categoria cadastrar(CategoriaInput categoriaInput) {
		Categoria categoria = CategoriaInput.to(categoriaInput);
		categoria.setId(null);
		categoria.setUsuario(gestaoSecurity.getUsuario());
		return repository.save(categoria);
	}
	
	public Categoria buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Categoria não encontrado id: " + id));
	}

	public Categoria buscar(String nome) {
		return repository.findByNome(nome, gestaoSecurity.getUsuario().getId())
				.orElseThrow(() -> new ObjetoNaoEncontrado("Categoria não encontrado nome: " + nome));
	}
	
	public List<CategoriaConsulta> listar(){
		CategoriaFiltro filtro = new CategoriaFiltro();
		filtro.setUsuario(gestaoSecurity.getUsuario());
		return repository.findAll(CategoriaSpec.comFiltro(filtro)).stream()
				.map( categoria -> new CategoriaConsulta(categoria)).collect(Collectors.toList());
	}
	
	public Categoria atualizar(CategoriaInput categoriaInput, Long id) {
		Categoria categoriaAtual = buscar(id);

		Categoria categoria = CategoriaInput.to(categoriaInput);
		categoria.setUsuario(gestaoSecurity.getUsuario());

		BeanUtils.copyProperties(categoria, categoriaAtual);
		return repository.save(categoria);
	}
	
	public void deletar(Long id) {
		buscar(id);
		repository.deleteById(id);
	}
	
}
