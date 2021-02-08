package com.bombeiros.siteinterno.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BgaDTO {
    
    private MultipartFile documento;

    private String nome;
	
    private int num_bga;

    public BgaDTO(MultipartFile documento, String nome, int num_bga) {
        this.documento = documento;
        this.nome = nome;
        this.num_bga = num_bga;
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

    public int getNum_bga() {
        return num_bga;
    }

    public void setNum_bga(int num_bga) {
        this.num_bga = num_bga;
    }

}
