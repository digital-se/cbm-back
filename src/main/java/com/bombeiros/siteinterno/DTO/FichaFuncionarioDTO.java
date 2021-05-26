package com.bombeiros.siteinterno.DTO;

import java.sql.Date;
import java.util.List;

public class FichaFuncionarioDTO {

    private long idFichaFuncionario;
    private String nome;
    private int numFicha;
    private Date dataInclusao;
    private Date dataExclusao;
    private List<ArquivoDTO> documentos;

    public FichaFuncionarioDTO(long idFichaFuncionario, String nome, int numFicha, Date dataInclusao,
            Date dataExclusao, List<ArquivoDTO> documentos) {
        this.idFichaFuncionario = idFichaFuncionario;
        this.nome = nome;
        this.numFicha = numFicha;
        this.dataInclusao = dataInclusao;
        this.dataExclusao = dataExclusao;
        this.documentos = documentos;
    }

    public FichaFuncionarioDTO(long idFichaFuncionario, String nome, int numFicha, Date dataInclusao,
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

    public List<ArquivoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ArquivoDTO> documentos) {
        this.documentos = documentos;
    }

}
