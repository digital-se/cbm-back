package com.bombeiros.siteinterno.DTO;

public class ImagemDTO {

    private Long id_imagem;

    private String name;

    private String type;

    private byte[] imagem;

    public ImagemDTO(Long id_imagem, String name, String type, byte[] imagem) {
        this.id_imagem = id_imagem;
        this.name = name;
        this.type = type;
        this.imagem = imagem;
    }

    public Long getId_imagem() {
        return id_imagem;
    }

    public void setId_imagem(Long id_imagem) {
        this.id_imagem = id_imagem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
    

    
}
