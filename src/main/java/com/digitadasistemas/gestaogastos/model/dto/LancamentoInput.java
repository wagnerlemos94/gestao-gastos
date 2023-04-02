package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.Status;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;;

@Getter
@Setter
@RequiredArgsConstructor
public class LancamentoInput {

    private final Long id;
    @NotNull(message = "O campo Categoria é Obrigatório")
    private final Long categoria;
    @NotBlank(message = "O campo Descrição é Obrigatório")
    private final String descricao;
    @NotNull(message = "O campo Tipo é Obrigatório")
    private final Integer tipo;
    @NotNull(message = "O campo Valor é Obrigatório")
    private final Double valor;
    @NotNull(message = "O campo Data é Obrigatório")
    private final Date data;
    @NotNull(message = "O campo Mês é Obrigatório")
    private final Integer mes;
    private final Integer status;
    private final Usuario usuario;
    private final Integer parcela;

    public static Lancamento to(LancamentoInput lancamentoInput){
        Status status = lancamentoInput.getStatus() != null ? Status.toEnum(lancamentoInput.getStatus()) : Status.PENDENTE;

        Lancamento lancamento = new Lancamento();
        Categoria categoria = new Categoria();
        categoria.setId(lancamentoInput.getCategoria());
        lancamento.setCategoria(categoria);
        lancamento.setId(lancamentoInput.getId());
        lancamento.setDescricao(lancamentoInput.getDescricao().toUpperCase());
        lancamento.setValor(lancamentoInput.getValor());
        lancamento.setTipo(TipoLancamento.toEnum(lancamentoInput.getTipo()).getCodigo());
        lancamento.setData(lancamentoInput.getData());
        lancamento.setMes(Mes.toEnum(lancamentoInput.getMes()));
        lancamento.setUsuario(lancamentoInput.getUsuario());
        lancamento.setStatus(status);

        return lancamento;
    }
}
