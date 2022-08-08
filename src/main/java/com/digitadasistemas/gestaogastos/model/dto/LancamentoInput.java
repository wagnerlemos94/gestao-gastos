package com.digitadasistemas.gestaogastos.model.dto;

import com.digitadasistemas.gestaogastos.model.entities.Categoria;
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
import java.util.Date;
import java.util.Locale;

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
    private final Usuario usuario;

    public static Lancamento to(LancamentoInput lancamentoInput){
        Lancamento lancamento = new Lancamento();
        Categoria categoria = new Categoria();
        categoria.setId(lancamentoInput.getCategoria());
        lancamento.setCategoria(categoria);
        lancamento.setId(lancamentoInput.getId());
        lancamento.setDescricao(lancamentoInput.getDescricao().toUpperCase());
        lancamento.setValor(lancamentoInput.getValor());
        lancamento.setTipo(TipoLancamento.toEnum(lancamentoInput.getTipo()).getCodigo());
        lancamento.setData(lancamentoInput.getData());
        lancamento.setUsuario(lancamentoInput.getUsuario());

        return lancamento;
    }
}
