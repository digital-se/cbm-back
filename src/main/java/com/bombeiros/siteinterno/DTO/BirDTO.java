package com.bombeiros.siteinterno.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BirDTO {
    
    private MultipartFile documento;

    private String nome;
	
    private int num_bir;

    public BirDTO(MultipartFile documento, String nome, int num_bir) {
        this.documento = documento;
        this.nome = nome;
        this.num_bir = num_bir;
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

    public int getNum_bir() {
        return num_bir;
    }

    public void setNum_bir(int num_bir) {
        this.num_bir = num_bir;
    }

    

}
