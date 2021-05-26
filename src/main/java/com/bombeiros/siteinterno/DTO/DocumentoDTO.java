package com.bombeiros.siteinterno.DTO;

import java.util.List;

public class DocumentoDTO {

    private long id;
    private String name;
    private int num;
    private List<ArquivoDTO> documentos;

    public DocumentoDTO(long id, String name, int num, List<ArquivoDTO> documentos) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.documentos = documentos;
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
        return documentos;
    }

    public void setDocumentos(List<ArquivoDTO> documentos) {
        this.documentos = documentos;
    }

}