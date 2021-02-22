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
@Table(name="REGISTRO_ANTIGO")
public class RegistroAntigo implements Serializable{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idRegistroAntigo;

	private String nome;


	@OneToMany(mappedBy = "registroAntigo", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public RegistroAntigo() {
		//do nothing
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public long getId() {
		return idRegistroAntigo;
	}

	public void setId(long idRegistroAntigo) {
		this.idRegistroAntigo = idRegistroAntigo;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	
}
