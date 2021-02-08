package com.bombeiros.siteinterno.message;

import java.util.List;

public class RegistroAntigoResponseFile {
    
    private Long id_registroAntigo;
    private String nome;
    private List<ResponseFile> documentos;

    

    public RegistroAntigoResponseFile(Long id_registroAntigo, String nome) {
        this.id_registroAntigo = id_registroAntigo;
        this.nome = nome;
    }

    public Long getId_registroAntigo() {
        return id_registroAntigo;
    }

    public void setId_registroAntigo(Long id_registroAntigo) {
        this.id_registroAntigo = id_registroAntigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RegistroAntigoResponseFile(Long id_registroAntigo, String nome, List<ResponseFile> documentos) {
        this.id_registroAntigo = id_registroAntigo;
        this.nome = nome;
        this.documentos = documentos;
    }

    public List<ResponseFile> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ResponseFile> documentos) {
        this.documentos = documentos;
    }

    
    
}
