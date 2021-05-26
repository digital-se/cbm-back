package com.bombeiros.siteinterno.DTO;

import java.util.List;

public class BgaDTO {

    private Long idBga;
    private String nome;
    private int numBga;
    private List<ArquivoDTO> documentos;

    public BgaDTO(Long idBga, String nome, int numBga, List<ArquivoDTO> documentos) {
        this.idBga = idBga;
        this.nome = nome;
        this.numBga = numBga;
        this.documentos = documentos;
    }

    public BgaDTO(Long idBga, String nome, int numBga) {
        this.idBga = idBga;
        this.nome = nome;
        this.numBga = numBga;
    }

    public Long getIdBga() {
        return idBga;
    }

    public void setIdBga(Long idBga) {
        this.idBga = idBga;
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
