package com.bombeiros.siteinterno.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BirDTO {
    
    private MultipartFile image;

    private String nome;
	
    private int num_bir;

    public BirDTO(MultipartFile image, String nome, int num_bir) {
        this.image = image;
        this.nome = nome;
        this.num_bir = num_bir;
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

    public int getNum_bir() {
        return num_bir;
    }

    public void setNum_bir(int num_bir) {
        this.num_bir = num_bir;
    }

    

}
