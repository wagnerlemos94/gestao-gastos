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

    public List<StatusLancamento> listar() {
        return montarListaStatusLancamento();

    }

    private  List<StatusLancamento> montarListaStatusLancamento(){
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
