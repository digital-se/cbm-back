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
@Table(name="BGO")
public class Bgo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idBgo;
	
	private String nome;
	
	private int numBgo;
	

	@OneToMany(mappedBy = "bgo", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public Bgo() {
		//do nothing
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumBgo() {
		return numBgo;
	}

	public void setNumBgo(int numBgo) {
		this.numBgo = numBgo;
	}

	public Bgo(String nome, int numBgo) {
		this.nome = nome;
		this.numBgo = numBgo;
	}

	public long getIdBgo() {
		return idBgo;
	}

	public void setIdBgo(long idBgo) {
		this.idBgo = idBgo;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

}