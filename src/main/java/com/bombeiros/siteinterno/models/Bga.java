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
@Table(name = "BGA")
public class Bga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idBga;

	@Column
	private String nome;

	@Column
	private int numBga;

	@OneToMany(mappedBy = "bga", cascade = CascadeType.ALL)
	private List<Arquivo> documentos;

	public Bga() {
		// do nothing
	}

	public Bga(String nome, int numBga) {
		this.nome = nome;
		this.numBga = numBga;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNum() {
		return numBga;
	}

	public void setNum(int numBga) {
		this.numBga = numBga;
	}

	public long getId() {
		return idBga;
	}

	public void setId(long idBga) {
		this.idBga = idBga;
	}

	public List<Arquivo> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Arquivo> documentos) {
		this.documentos = documentos;
	}

}
