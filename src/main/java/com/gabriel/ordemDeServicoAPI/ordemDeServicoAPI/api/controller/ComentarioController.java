package com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.api.model.ComentarioInput;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.api.model.ComentarioModel;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.exception.EntidadeNaoEncontradaException;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.Comentario;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.OrdemServico;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.repository.OrdemServicoRepository;
import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemSericoId}/comentarios")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping	
	public List<ComentarioModel> listar(@PathVariable Long ordemSericoId){
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemSericoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		return toCollectionModel(ordemServico.getComentarios());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemSericoId, @Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemSericoId, comentarioInput.getDescricao());
		return toModel(comentario);
	}
	
	private ComentarioModel toModel (Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios){
		return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
	}
}
