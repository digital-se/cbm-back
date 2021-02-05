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
	private long id_bgo;
	
	private String nome;
	
	private int num_bgo;
	

	@OneToMany(mappedBy = "bgo", cascade = CascadeType.ALL)
	private List<Imagem> imagens;
	
	public Bgo() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNum_bgo() {
		return num_bgo;
	}

	public void setNum_bgo(int num_bgo) {
		this.num_bgo = num_bgo;
	}

	public Bgo(String nome, int num_bgo) {
		this.nome = nome;
		this.num_bgo = num_bgo;
	}

	public long getId_bgo() {
		return id_bgo;
	}

	public void setId_bgo(long id_bgo) {
		this.id_bgo = id_bgo;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}


	
}