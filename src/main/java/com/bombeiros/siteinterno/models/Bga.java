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
	private long id_bga;
	
	@Column
	private String nome;
	
	@Column
	private int num_bga;

	@OneToMany(mappedBy = "bga", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public Bga() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNum_bga() {
		return num_bga;
	}

	public void setNum_bga(int num_bga) {
		this.num_bga = num_bga;
	}

	public long getId_bga() {
		return id_bga;
	}

	public void setId_bga(long id_bga) {
		this.id_bga = id_bga;
	}
	public Bga(String nome, int num_bga) {
		this.nome = nome;
		this.num_bga = num_bga;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
}
