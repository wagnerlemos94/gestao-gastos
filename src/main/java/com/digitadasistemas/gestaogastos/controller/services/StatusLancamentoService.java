package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.model.enuns.StatusPagamento;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StatusLancamentoService {

    @Data
    public static class StatusLancamento{
        private int id;
        private String nome;
    }

    public List<StatusLancamento> listar() {
        return montarListaStatusLancamento();

    }

    private  List<StatusLancamento> montarListaStatusLancamento(){
        List<StatusLancamento> listaStatus = new ArrayList<StatusLancamento>();
        for(StatusPagamento statusPagamento : StatusPagamento.values()){
            StatusLancamento statusLancamento = new StatusLancamento();
            statusLancamento.setId(statusPagamento.getCodigo());
            statusLancamento.setNome(statusPagamento.getDescricao());
            listaStatus.add(statusLancamento);
        }
        return listaStatus;
    }
}
