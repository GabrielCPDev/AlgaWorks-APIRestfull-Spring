package com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.api.model;

import java.time.OffsetDateTime;

public class ComentarioModel {

	private Long id;
	private String descricao;
	private OffsetDateTime dataEnviOffsetDateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public OffsetDateTime getDataEnviOffsetDateTime() {
		return dataEnviOffsetDateTime;
	}
	public void setDataEnviOffsetDateTime(OffsetDateTime dataEnviOffsetDateTime) {
		this.dataEnviOffsetDateTime = dataEnviOffsetDateTime;
	}
	
	
	
}
