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
	private long id_fichaFuncionario;
	
	private String nome;
	
	private int num_ficha;
	
	private Date data_inclusao;
	
	private Date data_exclusao;
	

	@OneToMany(mappedBy = "fichaFuncionario", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public FichaFuncionario() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNum_ficha() {
		return num_ficha;
	}

	public void setNum_ficha(int num_ficha) {
		this.num_ficha = num_ficha;
	}

	public Date getData_inclusao() {
		return data_inclusao;
	}

	public void setData_inclusao(Date data_inclusao) {
		this.data_inclusao = data_inclusao;
	}

	public Date getData_exclusao() {
		return data_exclusao;
	}

	public void setData_exclusao(Date data_exclusao) {
		this.data_exclusao = data_exclusao;
	}

	public long getId_fichaFuncionario() {
		return id_fichaFuncionario;
	}

	public void setId_fichaFuncionario(long id_fichaFuncionario) {
		this.id_fichaFuncionario = id_fichaFuncionario;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	

	
}
