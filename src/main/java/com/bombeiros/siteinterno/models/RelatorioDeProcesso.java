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
@Table(name="RELATORIO_DE_PROCESSO")
public class RelatorioDeProcesso implements Serializable {
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_relatorioDeProcesso;
	
	private int num_relatorio;
	
	
	@OneToMany(mappedBy = "relatorioDeProcesso", cascade = CascadeType.ALL)
	private List<Imagem> imagens;
	
	public RelatorioDeProcesso() {
		
	}
	

	public int getNum_relatorio() {
		return num_relatorio;
	}

	public void setNum_relatorio(int num_relatorio) {
		this.num_relatorio = num_relatorio;
	}

	public long getId_relatorioDeProcesso() {
		return id_relatorioDeProcesso;
	}

	public void setId_relatorioDeProcesso(long id_relatorioDeProcesso) {
		this.id_relatorioDeProcesso = id_relatorioDeProcesso;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	
}
