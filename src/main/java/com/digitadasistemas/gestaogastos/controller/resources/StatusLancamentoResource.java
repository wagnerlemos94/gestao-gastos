package com.digitadasistemas.gestaogastos.controller.resources;

import com.digitadasistemas.gestaogastos.controller.services.StatusLancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statusLancamento")
public class StatusLancamentoResource {

    @Autowired
    private StatusLancamentoService status;

    @GetMapping
    public List<StatusLancamentoService.StatusLancamento> listar(){
        return status.listar();
    }

}
