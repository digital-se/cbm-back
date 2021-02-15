package com.bombeiros.siteinterno.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="FICHA_FUNCIONARIO")
public class FichaFuncionario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idFichaFuncionario;
	
	private String nome;
	
	private int numFicha;
	
	private Date dataInclusao;
	
	private Date dataExclusao;

	@OneToMany(mappedBy = "fichaFuncionario", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public FichaFuncionario() {
		//do nothing
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumFicha() {
		return numFicha;
	}

	public void setNumFicha(int numFicha) {
		this.numFicha = numFicha;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public long getIdFichaFuncionario() {
		return idFichaFuncionario;
	}

	public void setIdFichaFuncionario(long idFichaFuncionario) {
		this.idFichaFuncionario = idFichaFuncionario;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

}
