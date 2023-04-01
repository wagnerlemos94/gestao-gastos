package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.model.enuns.Status;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StatusLancamentoService {

    @Data
    public static class StatusLancamento{
        private int id;
        private String descricao;
    }
//
//    public Status[] listar() {
//        return Status.values();
//
//    }

    public List<StatusLancamento> listar() {


        List<StatusLancamento> listaStatus = new ArrayList<StatusLancamento>();
        for(Status status : Status.values()){
            StatusLancamento statusLancamento = new StatusLancamento();
            statusLancamento.setId(status.getCodigo());
            statusLancamento.setDescricao(status.getDescricao());
            listaStatus.add(statusLancamento);

        }

        return listaStatus;

    }
}
