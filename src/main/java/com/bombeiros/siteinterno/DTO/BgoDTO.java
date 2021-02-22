package com.bombeiros.siteinterno.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BgoDTO {
    
    private MultipartFile documento;

    private String nome;
	
    private int numBgo;

    public BgoDTO(MultipartFile documento, String nome, int numBgo) {
        this.documento = documento;
        this.nome = nome;
        this.numBgo = numBgo;
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

    public int getNum() {
        return numBgo;
    }

    public void setNum(int numBgo) {
        this.numBgo = numBgo;
    }

    

}
