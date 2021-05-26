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
@Table(name = "RELATORIO_DE_PROCESSO")
public class RelatorioDeProcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRelatorioDeProcesso;

	private int numRelatorio;

	@OneToMany(mappedBy = "relatorioDeProcesso", cascade = CascadeType.ALL)
	private List<Arquivo> documentos;

	public RelatorioDeProcesso() {
		// do nothing
	}

	public RelatorioDeProcesso(int numRelatorio) {
		this.numRelatorio = numRelatorio;
	}

	public int getNum() {
		return numRelatorio;
	}

	public void setNum(int numRelatorio) {
		this.numRelatorio = numRelatorio;
	}

	public long getId() {
		return idRelatorioDeProcesso;
	}

	public void setId(long idRelatorioDeProcesso) {
		this.idRelatorioDeProcesso = idRelatorioDeProcesso;
	}

	public List<Arquivo> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Arquivo> documentos) {
		this.documentos = documentos;
	}

}
