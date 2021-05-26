package com.bombeiros.siteinterno.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENTOS")
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
    private String numero; //-registro
	@Column
    private String tipo;
	@Column
    private String nome; //-relatorio
	@Column
    private Date dataInclusao; //ficha
	@Column
    private Date dataExclusao; //ficha
	@Column
    private Boolean visivel;
	@OneToMany(mappedBy = "arquivo", cascade = CascadeType.ALL)
	private List<Arquivo> arquivos;

	public Document() {
		this.visivel = false;
	}

	//Bga, bgo, bir, registro
	public Document(String numero, String tipo, String nome) {
		this();
		this.numero = numero;
		this.tipo = tipo;
		this.nome = nome;
	}

	//relatorio
	public Document(String numero, String tipo) {
		this();
		this.numero = numero;
		this.tipo = tipo;
	}

	//ficha
	public Document(String numero, String tipo, String nome, Date dataInclusao, Date dataExclusao) {
		this();
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
