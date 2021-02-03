package com.bombeiros.siteinterno.message;

public class BgaResponseFile {
    
    private Long id_bga;
    private String nome;
    private int num_bga;

    public BgaResponseFile(Long id_bga, String nome, int num_bga) {
        this.id_bga = id_bga;
        this.nome = nome;
        this.num_bga = num_bga;
    }

    public Long getId_bga() {
        return id_bga;
    }

    public void setId_bga(Long id_bga) {
        this.id_bga = id_bga;
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
