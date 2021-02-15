package com.bombeiros.siteinterno.dto;

import java.util.List;

import com.bombeiros.siteinterno.models.Documento;

public class RegistroAntigoDTO {

    private long idRegistroAntigo;

	private String nome;

	private List<Documento> documentos;

    public RegistroAntigoDTO(long idRegistroAntigo, String nome, List<Documento> documentos) {
        this.idRegistroAntigo = idRegistroAntigo;
        this.nome = nome;
        this.documentos = documentos;
    }

    public long getIdRegistroAntigo() {
        return idRegistroAntigo;
    }

    public void setIdRegistroAntigo(long idRegistroAntigo) {
        this.idRegistroAntigo = idRegistroAntigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
    
    

}
