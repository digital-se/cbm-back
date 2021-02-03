package com.bombeiros.siteinterno.message;

import java.util.List;

public class RegistroAntigoResponseFile {
    
    private Long id_registroAntigo;
    private String nome;
    private List<ResponseFile> imagens;

    

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

    public RegistroAntigoResponseFile(Long id_registroAntigo, String nome, List<ResponseFile> imagens) {
        this.id_registroAntigo = id_registroAntigo;
        this.nome = nome;
        this.imagens = imagens;
    }

    public List<ResponseFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<ResponseFile> imagens) {
        this.imagens = imagens;
    }

    
    
}
