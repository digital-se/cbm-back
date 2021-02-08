package com.bombeiros.siteinterno.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BgoDTO {
    
    private MultipartFile documento;

    private String nome;
	
    private int num_bgo;

    public BgoDTO(MultipartFile documento, String nome, int num_bgo) {
        this.documento = documento;
        this.nome = nome;
        this.num_bgo = num_bgo;
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

    public int getNum_bgo() {
        return num_bgo;
    }

    public void setNum_bgo(int num_bgo) {
        this.num_bgo = num_bgo;
    }

    

}
