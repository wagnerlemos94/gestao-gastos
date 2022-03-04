package com.digitadasistemas.gestaogastos.controller.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria cadastrar(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}
	
	public Categoria buscar(Long id) {
		return repository.findById(id).get();
	}
	
	public List<Categoria> listar(){
		return repository.findAll();
	}
	
	public Categoria atualizar(Categoria categoria, Long id) {
		categoria.setId(id);
		return repository.save(categoria);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
}
