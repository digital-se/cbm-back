package com.bombeiros.siteinterno.message;

import java.sql.Date;
import java.util.List;

public class FichaFuncionarioResponseFile {
    
    private long idFichaFuncionario;
	
	private String nome;
	
	private int numFicha;
	
	private Date dataInclusao;
	
    private Date dataExclusao;
    
    private List<ResponseFile> documentos;

    public FichaFuncionarioResponseFile(long idFichaFuncionario, String nome, int numFicha, Date dataInclusao,
            Date dataExclusao, List<ResponseFile> documentos) {
        this.idFichaFuncionario = idFichaFuncionario;
        this.nome = nome;
        this.numFicha = numFicha;
        this.dataInclusao = dataInclusao;
        this.dataExclusao = dataExclusao;
        this.documentos = documentos;
    }

    public FichaFuncionarioResponseFile(long idFichaFuncionario, String nome, int numFicha, Date dataInclusao,
            Date dataExclusao) {
        this.idFichaFuncionario = idFichaFuncionario;
        this.nome = nome;
        this.numFicha = numFicha;
        this.dataInclusao = dataInclusao;
        this.dataExclusao = dataExclusao;
    }

    public long getIdFichaFuncionario() {
        return idFichaFuncionario;
    }

    public void setIdFichaFuncionario(long idFichaFuncionario) {
        this.idFichaFuncionario = idFichaFuncionario;
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

    public List<ResponseFile> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ResponseFile> documentos) {
        this.documentos = documentos;
    }

    
}
