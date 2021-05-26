package com.bombeiros.siteinterno.DTO;

import java.util.List;

public class DocumentDTO {

    private long id;
    private String name;
    private int num;
    private List<ArquivoDTO> arquivos;

    public DocumentDTO(long id, String name, int num, List<ArquivoDTO> arquivos) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.arquivos = arquivos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<ArquivoDTO> getDocumentos() {
        return arquivos;
    }

    public void setDocumentos(List<ArquivoDTO> arquivos) {
        this.arquivos = arquivos;
    }

}