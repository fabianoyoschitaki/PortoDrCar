package com.portoseguro.portodrcar.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.portoseguro.portodrcar.enums.ImportanciaLembreteEnum;
import com.portoseguro.portodrcar.enums.TipoLembreteEnum;

public class Agendamento implements Serializable {
	private Integer id;
	private ImportanciaLembreteEnum importancia;
	private String titulo;
	private String descricao;
	private Date dataAgendamento;
	private String nomeEstabelecimento;
	private String enderecoEstabelecimento;
	private String telefoneEstabelecimento;
	private Boolean jaFoiExecutado;
	
	public Agendamento(Integer id, String titulo, String descricao,
			Date dataAgendamento, String nomeEstabelecimento,
			String enderecoEstabelecimento, String telefoneEstabelecimento, 
			ImportanciaLembreteEnum importancia) {
		super();
		this.importancia = importancia;
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataAgendamento = dataAgendamento;
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.enderecoEstabelecimento = enderecoEstabelecimento;
		this.telefoneEstabelecimento = telefoneEstabelecimento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}
	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}
	public String getEnderecoEstabelecimento() {
		return enderecoEstabelecimento;
	}
	public void setEnderecoEstabelecimento(String enderecoEstabelecimento) {
		this.enderecoEstabelecimento = enderecoEstabelecimento;
	}
	public String getTelefoneEstabelecimento() {
		return telefoneEstabelecimento;
	}
	public void setTelefoneEstabelecimento(String telefoneEstabelecimento) {
		this.telefoneEstabelecimento = telefoneEstabelecimento;
	}
	public Boolean getJaFoiExecutado() {
		return jaFoiExecutado;
	}
	public void setJaFoiExecutado(Boolean jaFoiExecutado) {
		this.jaFoiExecutado = jaFoiExecutado;
	}
	public ImportanciaLembreteEnum getImportancia() {
		return importancia;
	}
	public void setImportancia(ImportanciaLembreteEnum importancia) {
		this.importancia = importancia;
	}
}
