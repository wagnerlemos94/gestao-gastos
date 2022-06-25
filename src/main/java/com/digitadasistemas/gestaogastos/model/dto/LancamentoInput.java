package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Locale;

@Getter
@Setter
@RequiredArgsConstructor
public class LancamentoInput {

    private final Long id;
    @NotBlank(message = "O campo Categoria é Obrigatório")
    private final String categoria;
    @NotBlank(message = "O campo Descrição é Obrigatório")
    private final String descricao;
    @NotBlank(message = "O campo Mês é Obrigatório")
    private final String mes;
    @NotBlank(message = "O campo Tipo é Obrigatório")
    private final String tipo;
    @NotNull(message = "O campo Valor é Obrigatório")
    private final Double valor;
    private final Usuario usuario;

    public static Lancamento to(LancamentoInput lancamentoInput){
        Lancamento lancamento = new Lancamento();
        lancamento.setId(lancamentoInput.getId());
        lancamento.setDescricao(lancamentoInput.getDescricao().toUpperCase());
        lancamento.setValor(lancamentoInput.getValor());
        lancamento.setTipo(TipoLancamento.toEnum(lancamentoInput.getTipo()).getCodigo());
        lancamento.setMes(Mes.toEnum(lancamentoInput.getMes()).getCodigo());
        lancamento.setUsuario(lancamentoInput.getUsuario());

        return lancamento;
    }
}
