package com.bombeiros.siteinterno.message;

import java.util.List;

public class RegistroAntigoResponseFile {
    
    private Long idRegistroAntigo;
    private String nome;
    private List<ResponseFile> documentos;

    

    public RegistroAntigoResponseFile(Long idRegistroAntigo, String nome) {
        this.idRegistroAntigo = idRegistroAntigo;
        this.nome = nome;
    }

    public Long getIdRegistroAntigo() {
        return idRegistroAntigo;
    }

    public void setIdRegistroAntigo(Long idRegistroAntigo) {
        this.idRegistroAntigo = idRegistroAntigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RegistroAntigoResponseFile(Long idRegistroAntigo, String nome, List<ResponseFile> documentos) {
        this.idRegistroAntigo = idRegistroAntigo;
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
