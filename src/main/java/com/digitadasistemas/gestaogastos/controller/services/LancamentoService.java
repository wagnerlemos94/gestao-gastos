package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.config.GestaoSecurity;
import com.digitadasistemas.gestaogastos.controller.services.exception.ObjetoNaoEncontrado;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoConsultaValoresDTO;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoInput;
import com.digitadasistemas.gestaogastos.model.dto.LancamentoValoresDTO;
import com.digitadasistemas.gestaogastos.model.entities.Categoria;
import com.digitadasistemas.gestaogastos.model.entities.Lancamento;
import com.digitadasistemas.gestaogastos.model.enuns.Mes;
import com.digitadasistemas.gestaogastos.model.enuns.TipoLancamento;
import com.digitadasistemas.gestaogastos.model.filtro.LancamentoFiltro;
import com.digitadasistemas.gestaogastos.model.params.LancamentoUpdateStatusParams;
import com.digitadasistemas.gestaogastos.model.repositories.LancamentoRepository;
import com.digitadasistemas.gestaogastos.model.repositories.LancamentoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentorepository;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private GestaoSecurity gestaoSecurity;

	@Transactional
	public void cadastrar(LancamentoInput lancamentoInput) {
		int ano = lancamentoInput.getAno();
		int mesDeEntrada = lancamentoInput.getMes();
		int contator = 0;
		for (int i=0;i<lancamentoInput.getParcela();i++){
			Lancamento lancamento = LancamentoInput.to(lancamentoInput);
			lancamento.setMes(Mes.toEnum(mesDeEntrada + contator));
			lancamento.setAno(ano);
			contator ++;
			if(lancamento.getMes().equals(Mes.DEZEMBRO)){
				contator = 0;
				mesDeEntrada = 1;
				ano++;
			}
		lancamento.setUsuario(gestaoSecurity.getUsuario());
		lancamento.setCategoria(categoriaService.buscar(lancamentoInput.getCategoria()));
		lancamento.setData(addData(lancamento.getData(), i));
		lancamentorepository.save(lancamento);
		}
	}

	private Date addData(Date data,Integer parcela){
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.MONTH, parcela);
		data = c.getTime();
		return data;
	}

	public Lancamento buscar(Long id) {
		return lancamentorepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Lancamento não encontrado id: " + id));
	}

	public LancamentoValoresDTO valores(LancamentoFiltro filtro){
		filtro.setUsuario(gestaoSecurity.getUsuario());
		List<Lancamento> lancamentos = lancamentorepository.findAll(LancamentoSpec.comFiltro(filtro));
		return calculoValores(lancamentos);
	}

	private LancamentoValoresDTO calculoValores(List<Lancamento> lancamentos) {
		LancamentoValoresDTO lancamentosTotal = new LancamentoValoresDTO();
		lancamentosTotal.setRecebido(0.0);
		lancamentosTotal.setGasto(0.0);
		lancamentosTotal.setSaldo(0.0);

		lancamentos.forEach(
				lancamento -> {
					if(lancamento.getTipo().equals(TipoLancamento.RECEITA)) {
						lancamentosTotal.setRecebido(lancamentosTotal.getRecebido() + lancamento.getValor() );
					}else if(lancamento.getTipo().equals(TipoLancamento.DESPESA)){
						lancamentosTotal.setGasto(lancamentosTotal.getGasto() + lancamento.getValor());
					}
				});

		lancamentosTotal.setSaldo(lancamentosTotal.getRecebido() - lancamentosTotal.getGasto());

		return lancamentosTotal;
	}

	private List<LancamentoConsultaDTO> converteDto(List<Lancamento> lancamentos){
		return lancamentos.stream()
				.map(
						lancamento -> new LancamentoConsultaDTO(lancamento)
				).collect(Collectors.toList());
	}

	public List<LancamentoConsultaDTO> listar(LancamentoFiltro filtro) {

		filtro.setUsuario(gestaoSecurity.getUsuario());
		List<LancamentoConsultaDTO>lancamentos = converteDto(lancamentorepository.findAll(LancamentoSpec.comFiltro(filtro)));

		return calculoValorTotal(lancamentos);
	}

	public List<LancamentoConsultaValoresDTO> listarAgrupado(LancamentoFiltro filtro) throws ParseException {

		return lancamentorepository.buscarTodos(
				gestaoSecurity.getUsuario(), Mes.toEnum(filtro.getMes()), filtro.getAno());
//		return lancamentorepository.buscarTodos(gestaoSecurity.getUsuario(), Ultil.formataData(filtro.getDataInicio()), Ultil.formataData(filtro.getDataFinal()));

	}

	public int atualizarStatus(LancamentoUpdateStatusParams params){

		params.setUsuario(gestaoSecurity.getUsuario().getId().intValue());
		params.setAno(LocalDate.now().getYear());

		return lancamentorepository.atualizarStatus(params);
	}

	public List<LancamentoConsultaDTO> buscarLancamentoPorCategoriaETipo(LancamentoFiltro filtro){
			return converteDto(lancamentorepository.findAll(LancamentoSpec.comFiltro(filtro)));
	}

	@Transactional
	public void atualizar(Long id, LancamentoInput lancamentoInput) {
		Lancamento lancamentoAtual = buscar(id);

		Categoria categoria = categoriaService.buscar(lancamentoInput.getCategoria());

		Lancamento lancamento = LancamentoInput.to(lancamentoInput);
		lancamento.setCategoria(categoria);
		lancamento.setUsuario(gestaoSecurity.getUsuario());
		lancamento.setAno(lancamentoAtual.getAno());

		copyProperties(lancamento, lancamentoAtual);
		lancamentorepository.save(lancamento);
	}

	private List<LancamentoConsultaDTO> calculoValorTotal(List<LancamentoConsultaDTO> lancamentos) {
		LancamentoConsultaDTO lancamentosTotal = new LancamentoConsultaDTO();
		lancamentosTotal.setTipo("SALDO");
		lancamentosTotal.setValor(0.0);
		
		lancamentos.forEach(
				lancamento -> {
					if(lancamento.getTipo().equals(TipoLancamento.RECEITA.getDescricao())) {
						lancamentosTotal.setValor(lancamentosTotal.getValor() + lancamento.getValor());
					}else {
						lancamentosTotal.setValor(lancamentosTotal.getValor() - lancamento.getValor());
					}
				});
		lancamentos.add(lancamentosTotal);
		
		return lancamentos;
	}

	@Transactional
	public void delete(Long id){
		lancamentorepository.delete(buscar(id));
	}

	public List<Lancamento> findAllLancamentosPendentes(){
		return  lancamentorepository.findAllLancamentosPendentes();
	}

}
