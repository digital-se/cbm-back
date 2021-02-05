package com.bombeiros.siteinterno.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BgoDTO {
    
    private MultipartFile image;

    private String nome;
	
    private int num_bgo;

    public BgoDTO(MultipartFile image, String nome, int num_bgo) {
        this.image = image;
        this.nome = nome;
        this.num_bgo = num_bgo;
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

    public int getNum_bgo() {
        return num_bgo;
    }

    public void setNum_bgo(int num_bgo) {
        this.num_bgo = num_bgo;
    }

    

}
