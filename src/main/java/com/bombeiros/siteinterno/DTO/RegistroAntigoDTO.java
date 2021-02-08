package com.bombeiros.siteinterno.DTO;

import java.util.List;

import com.bombeiros.siteinterno.models.Documento;

public class RegistroAntigoDTO {

    private long id_registroAntigo;

	private String nome;

	private List<Documento> documentos;

    public RegistroAntigoDTO(long id_registroAntigo, String nome, List<Documento> documentos) {
        this.id_registroAntigo = id_registroAntigo;
        this.nome = nome;
        this.documentos = documentos;
    }

    public long getId_registroAntigo() {
        return id_registroAntigo;
    }

    public void setId_registroAntigo(long id_registroAntigo) {
        this.id_registroAntigo = id_registroAntigo;
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
