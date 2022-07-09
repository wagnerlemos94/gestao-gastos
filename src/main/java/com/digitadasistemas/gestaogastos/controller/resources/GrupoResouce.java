package com.digitadasistemas.gestaogastos.controller.resources;

import com.digitadasistemas.gestaogastos.controller.services.GrupoService;
import com.digitadasistemas.gestaogastos.model.dto.GrupoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.GrupoInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoResouce {

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastro(@RequestBody @Valid GrupoInputDTO grupoInputDTO){
        grupoService.cadastro(grupoInputDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GrupoConsultaDTO> buscar(){
        return grupoService.buscar();
    }

}
