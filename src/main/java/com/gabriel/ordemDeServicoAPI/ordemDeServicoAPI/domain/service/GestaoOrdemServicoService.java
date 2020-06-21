package com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.exception.EntidadeNaoEncontradaException;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.exception.NegocioException;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.Cliente;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.Comentario;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.OrdemServico;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.StatusOrdemServico;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.repository.ClienteRepository;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.repository.ComentarioRepositoy;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepositoy comentarioRepository;
	
	public OrdemServico criar( OrdemServico ordemServico) {
	
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
		
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}

	private OrdemServico buscar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		return ordemServico;
	}
}
