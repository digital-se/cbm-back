package com.bombeiros.siteinterno.DTO;

import java.util.Date;

public class FichaFuncionarioDTO {

    private long idFichaFuncionario;
	
	private String nome;
	
	private int numFicha;
	
	private Date dataInclusao;
	
	private Date dataExclusao;

    public FichaFuncionarioDTO(long idFichaFuncionario, String nome, int numFicha, Date dataInclusao,
            Date dataExclusao) {
        this.idFichaFuncionario = idFichaFuncionario;
        this.nome = nome;
        this.numFicha = numFicha;
        this.dataInclusao = dataInclusao;
        this.dataExclusao = dataExclusao;
    }

    public long getId() {
        return idFichaFuncionario;
    }

    public void setId(long idFichaFuncionario) {
        this.idFichaFuncionario = idFichaFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum() {
        return numFicha;
    }

    public void setNum(int numFicha) {
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
	
    

}
