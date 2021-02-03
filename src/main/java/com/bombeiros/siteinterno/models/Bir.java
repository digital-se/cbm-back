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
	private long id_bir;
	
	private String nome;
	
	private int num_bir;


	@OneToMany(mappedBy = "bir", cascade = CascadeType.ALL)
	private List<Imagem> imagens;
	
	public Bir() {
		
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getNum_bir() {
		return num_bir;
	}

	public void setNum_bir(int num_bir) {
		this.num_bir = num_bir;
	}

	public long getId_bir() {
		return id_bir;
	}

	public void setId_bir(long id_bir) {
		this.id_bir = id_bir;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	
	
}
