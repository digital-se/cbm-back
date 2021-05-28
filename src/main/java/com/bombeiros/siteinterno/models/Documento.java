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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENTO")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ManyToMany
    @JoinTable(name = "FK_Doc_Mil", joinColumns = @JoinColumn(name = "documento_id"), inverseJoinColumns = @JoinColumn(name = "militar_id"))
	private String documento_id;
	@Column
    private String tipo;
	@Column
    private String nome;
	@ManyToOne(optional=false) 
	@JoinColumn(name="militar_id", nullable=false, updatable=false)
    private String criador;
	@Column
    private Date dataHoraCadastro;
	@Column
    private Boolean visivel;
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
	private List<Arquivo> arquivos;

	public Documento(String tipo, String nome, Date dataHoraCadastro, Boolean visivel) {
		this.tipo = tipo;
		this.nome = nome;
		this.dataHoraCadastro = dataHoraCadastro;
		this.visivel = visivel;
	}

	public String getId() {
		return documento_id;
	}

	public void setId(String id) {
		this.documento_id = id;
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
