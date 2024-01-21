package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.config.GestaoSecurity;
import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoJaExiste;
import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.dto.CategoriaConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.CategoriaInput;
import com.digitadasistemas.gestaogastos.model.dto.CategoriaListaNomeDTO;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import com.digitadasistemas.gestaogastos.model.enuns.Status;
import com.digitadasistemas.gestaogastos.model.filtro.CategoriaFiltro;
import com.digitadasistemas.gestaogastos.model.repositories.CategoriaRepository;
import com.digitadasistemas.gestaogastos.model.repositories.CategoriaSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private GestaoSecurity gestaoSecurity;
	
	public Categoria cadastrar(CategoriaInput categoriaInput) {

		Categoria categoria = CategoriaInput.to(categoriaInput);
		categoria.setUsuario(gestaoSecurity.getUsuario());
		Categoria categoriaExiste = isCadastrado(categoria.getNome());

		if(categoriaExiste != null){
			categoria.setId(categoriaExiste.getId());
			categoria.setStatus(Status.ATIVO);
		}

		return repository.save(categoria);
	}

	private Categoria isCadastrado(String nome){
		Categoria categoria = findByNome(nome);
		if(categoria != null && categoria.getStatus().getDescricao() != Status.EXCLUIDO.getDescricao()){
			throw new ObjetoJaExiste("Categoria " + nome + ", Já cadastrada");
		}

		return categoria;
	}

	public Categoria cadastrar(Categoria categoria) {
		return repository.save(categoria);
	}
	
	public Categoria buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Categoria não encontrado id: " + id));
	}

	public List<CategoriaListaNomeDTO> buscarPorGrupo(Grupo grupo) {
		return repository.findByStatusAndGrupo(Status.ATIVO,grupo).stream()
				.map( categoria -> new CategoriaListaNomeDTO(categoria)).collect(Collectors.toList());
	}

	public List<CategoriaConsultaDTO> listar(){
		CategoriaFiltro filtro = new CategoriaFiltro();
		filtro.setUsuario(gestaoSecurity.getUsuario());
		return repository.findAll(CategoriaSpec.comFiltro(filtro)).stream()
				.map( categoria -> new CategoriaConsultaDTO(categoria)).collect(Collectors.toList());
	}

	public List<CategoriaConsultaDTO> listarAtivos(boolean ativo){
		CategoriaFiltro filtro = new CategoriaFiltro();
		filtro.setAtivo(ativo);
		filtro.setUsuario(gestaoSecurity.getUsuario());
		return repository.findAll(CategoriaSpec.comFiltro(filtro)).stream()
				.map( categoria -> new CategoriaConsultaDTO(categoria)).collect(Collectors.toList());
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

	public Categoria findByNome(String nome){
		return repository.findByNomeAndUsuario(nome, gestaoSecurity.getUsuario());
	}

	public void excluir(Long id){
		Categoria categoria = buscar(id);
		categoria.setStatus(Status.EXCLUIDO);
		repository.save(categoria);
	}

	public Categoria ativo(Long id, boolean ativo){
		Categoria categoria = buscar(id);
		Status status = ativo ? Status.ATIVO : Status.INATIVO;
		categoria.setStatus(status);
		return repository.save(categoria);
	}
}