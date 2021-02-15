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
@Table(name="BGA")
public class Bga implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idBga;
	
	@Column
	private String nome;
	
	@Column
	private int numBga;

	@OneToMany(mappedBy = "bga", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public Bga() {
		//do nothing
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumBga() {
		return numBga;
	}

	public void setNumBga(int numBga) {
		this.numBga = numBga;
	}

	public long getIdBga() {
		return idBga;
	}

	public void setIdBga(long idBga) {
		this.idBga = idBga;
	}
	public Bga(String nome, int numBga) {
		this.nome = nome;
		this.numBga = numBga;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
}
