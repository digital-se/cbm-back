package com.bombeiros.siteinterno.DTO;

import java.util.Date;
import java.util.List;

import com.bombeiros.siteinterno.models.Arquivo;
import com.bombeiros.siteinterno.models.Militar;

public class DocumentoDTO {
	
	private long documento_id;
    private String tipo;
    private String nome;
    private Militar criador;
    private Date dataHoraCadastro;
    private Boolean visivel;
	private List<Arquivo> arquivos;

	public DocumentoDTO(long documento_id, String tipo, String nome, Militar criador, Date dataHoraCadastro,
			Boolean visivel, List<Arquivo> arquivos) {
		this.documento_id = documento_id;
		this.tipo = tipo;
		this.nome = nome;
		this.criador = criador;
		this.dataHoraCadastro = dataHoraCadastro;
		this.visivel = visivel;
		this.arquivos = arquivos;
	}

	public long getDocumento_id() {
		return documento_id;
	}

	public void setDocumento_id(long documento_id) {
		this.documento_id = documento_id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Militar getCriador() {
		return criador;
	}

	public void setCriador(Militar criador) {
		this.criador = criador;
	}

	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public Boolean getVisivel() {
		return visivel;
	}

	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
	
	
	

}
