package com.bombeiros.siteinterno.DTO;

import java.util.Date;
import java.util.List;

import com.bombeiros.siteinterno.models.Arquivo;

public class DocumentoDTO {
	
	private Long id;
    private String numero; //-registro
    private String tipo;
    private String nome; //-relatorio
    private Date dataInclusao; //ficha
    private Date dataExclusao; //ficha
    private Boolean visivel;
	private List<Arquivo> arquivos;

	public DocumentoDTO(Boolean visivel) {
		this.visivel = visivel;
	}

	//Bga, bgo, bir, registro
	public DocumentoDTO(String numero, String tipo, String nome, Boolean visivel) {
		this(visivel);
		this.numero = numero;
		this.tipo = tipo;
		this.nome = nome;
	}

	//relatorio
	public DocumentoDTO(String numero, String tipo, Boolean visivel) {
		this(visivel);
		this.numero = numero;
		this.tipo = tipo;
	}

	//ficha
	public DocumentoDTO(String numero, String tipo, String nome, Date dataInclusao, Date dataExclusao, Boolean visivel) {
		this(visivel);
		this.numero = numero;
		this.tipo = tipo;
		this.nome = nome;
		this.dataInclusao = dataInclusao;
		this.dataExclusao = dataExclusao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
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
