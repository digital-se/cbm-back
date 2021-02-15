package com.bombeiros.siteinterno.dto;

import org.springframework.web.multipart.MultipartFile;

public class BgaDTO {
    
    private MultipartFile documento;

    private String nome;
	
    private int numBga;

    public BgaDTO(MultipartFile documento, String nome, int numBga) {
        this.documento = documento;
        this.nome = nome;
        this.numBga = numBga;
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

    public int getNumBga() {
        return numBga;
    }

    public void setNumBga(int numBga) {
        this.numBga = numBga;
    }

}
