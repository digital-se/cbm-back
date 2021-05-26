package com.bombeiros.siteinterno.models;

import java.io.Serializable;
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
	private long id;

	@Column
	private String nome;

	@Column
	private int num;

	@OneToMany(mappedBy = "documentos", cascade = CascadeType.ALL)
	private List<Arquivo> documentos;

	public Document() {
		// do nothing
	}

	public Document(String nome, int num) {
		this.nome = nome;
		this.num = num;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Arquivo> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Arquivo> documentos) {
		this.documentos = documentos;
	}

}
