package com.portoseguro.portodrcar.model;

import java.io.Serializable;
import java.util.Calendar;

import com.portoseguro.portodrcar.enums.ImportanciaLembreteEnum;
import com.portoseguro.portodrcar.enums.TipoLembreteEnum;

public class Lembrete implements Serializable {
	private Integer id;
	private ImportanciaLembreteEnum importancia;
	private TipoLembreteEnum tipo;
	private String titulo;
	private String descricao;
	private String frequencia;
	private Calendar dataExecucao;

	public Lembrete(Integer id, ImportanciaLembreteEnum importancia,
			TipoLembreteEnum tipo, String titulo, String descricao,
			String frequencia, Calendar dataExecucao) {
		super();
		this.id = id;
		this.importancia = importancia;
		this.tipo = tipo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.frequencia = frequencia;
		this.dataExecucao = dataExecucao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImportanciaLembreteEnum getImportancia() {
		return importancia;
	}

	public void setImportancia(ImportanciaLembreteEnum importancia) {
		this.importancia = importancia;
	}

	public TipoLembreteEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoLembreteEnum tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public Calendar getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Calendar dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
}
