package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.config.GestaoSecurity;
import com.digitadasistemas.gestaogastos.model.dto.GrupoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.GrupoInputDTO;
import com.digitadasistemas.gestaogastos.model.entities.Grupo;
import com.digitadasistemas.gestaogastos.model.repositories.GrupoRepository;
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
    private GestaoSecurity gestaoSecurity;

    @Transactional(readOnly = true)
    public List<GrupoConsultaDTO> buscar(){
        return grupoRepository.findAll().stream().map(
                grupo -> new GrupoConsultaDTO(grupo)
        ).collect(Collectors.toList());
    }

    @Transactional
    public void cadastro(GrupoInputDTO grupoInputDTO){
        Grupo grupo = GrupoInputDTO.to(grupoInputDTO);
        grupo.setUsuario(gestaoSecurity.getUsuario());
        grupoRepository.save(grupo);
    }

}
