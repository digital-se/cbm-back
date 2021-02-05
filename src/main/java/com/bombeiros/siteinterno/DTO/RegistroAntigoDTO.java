package com.bombeiros.siteinterno.DTO;

import java.util.List;

import com.bombeiros.siteinterno.models.Imagem;

public class RegistroAntigoDTO {

    private long id_registroAntigo;

	private String nome;

	private List<Imagem> imagens;

    public RegistroAntigoDTO(long id_registroAntigo, String nome, List<Imagem> imagens) {
        this.id_registroAntigo = id_registroAntigo;
        this.nome = nome;
        this.imagens = imagens;
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

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }
    
    

}
