package com.bombeiros.siteinterno.DTO;

import java.util.Date;
import java.util.List;

import com.bombeiros.siteinterno.models.Arquivo;

public class DocumentoDTO {
	
	private String id;
    private String tipo;
    private String nome;
    private Date dataHoraCadastro;
    private Boolean visivel;
	private List<Arquivo> arquivos;

	public DocumentoDTO(String id, String tipo, String nome, Date dataHoraCadastro, Boolean visivel,
			List<Arquivo> arquivos) {
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.dataHoraCadastro = dataHoraCadastro;
		this.visivel = visivel;
		this.arquivos = arquivos;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
