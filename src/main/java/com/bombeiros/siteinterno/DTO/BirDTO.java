package com.bombeiros.siteinterno.dto;

import org.springframework.web.multipart.MultipartFile;

public class BirDTO {
    
    private MultipartFile documento;

    private String nome;
	
    private int numBir;

    public BirDTO(MultipartFile documento, String nome, int numBir) {
        this.documento = documento;
        this.nome = nome;
        this.numBir = numBir;
    }

    public MultipartFile getDocumento() {
        return documento;
    }

    public void setDocumento(MultipartFile documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumBir() {
        return numBir;
    }

    public void setNumBir(int numBir) {
        this.numBir = numBir;
    }  

}
