package com.bombeiros.siteinterno.message;

public class BirResponseFile {
    
    private long id_bir;
	
	private String nome;
	
    private int num_bir;

    public BirResponseFile(long id_bir, String nome, int num_bir) {
        this.id_bir = id_bir;
        this.nome = nome;
        this.num_bir = num_bir;
    }

    public long getId_bir() {
        return id_bir;
    }

    public void setId_bir(long id_bir) {
        this.id_bir = id_bir;
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
