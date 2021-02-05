package com.bombeiros.siteinterno.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BgaDTO {
    
    private MultipartFile image;

    private String nome;
	
    private int num_bga;

    public BgaDTO(MultipartFile image, String nome, int num_bga) {
        this.image = image;
        this.nome = nome;
        this.num_bga = num_bga;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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
