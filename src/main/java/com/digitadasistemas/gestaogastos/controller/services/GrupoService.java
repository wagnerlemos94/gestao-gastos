package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.config.GestaoSecurity;
import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.dto.GrupoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.GrupoInputDTO;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import com.digitadasistemas.gestaogastos.model.repositories.CategoriaRepository;
import com.digitadasistemas.gestaogastos.model.repositories.GrupoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private GestaoSecurity gestaoSecurity;

    @Transactional(readOnly = true)
    public List<GrupoConsultaDTO> buscar(){
        return grupoRepository.findAllByUsuario(gestaoSecurity.getUsuario()).stream().map(
                grupo -> {
                    GrupoConsultaDTO grupoDTO = new GrupoConsultaDTO(grupo);
                    grupoDTO.setCategorias(categoriaService.buscarPorGrupo(grupo));
                    return grupoDTO;
                }
        ).collect(Collectors.toList());
    }

    @Transactional
    public void cadastro(GrupoInputDTO grupoInputDTO){
        Grupo grupo = GrupoInputDTO.to(grupoInputDTO);
        grupo.setUsuario(gestaoSecurity.getUsuario());
        grupoRepository.save(grupo);
    }

    public Grupo buscar(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontrado("Grupo não encontrado id: " + id));
    }

    @Transactional
    public void atualizar(Long id, GrupoInputDTO grupoInputDTO) {
        buscar(id);
        Grupo grupo = GrupoInputDTO.to(grupoInputDTO);
        grupo.setId(id);
        grupo.setUsuario(gestaoSecurity.getUsuario());
        grupoRepository.save(grupo);
    }

}
