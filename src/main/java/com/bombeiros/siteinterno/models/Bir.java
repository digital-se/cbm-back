package com.bombeiros.siteinterno.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="BIR")
public class Bir implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idBir;
	
	private String nome;
	
	private int numBir;


	@OneToMany(mappedBy = "bir", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public Bir() {
		//do nothing
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNum() {
		return numBir;
	}

	public void setNum(int numBir) {
		this.numBir = numBir;
	}

	public long getId() {
		return idBir;
	}

	public void setId(long idBir) {
		this.idBir = idBir;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
}
