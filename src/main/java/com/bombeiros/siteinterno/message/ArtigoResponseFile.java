package com.bombeiros.siteinterno.message;

import java.util.List;

public class ArtigoResponseFile {

    private long id;
    private String name;
    private int num;
    private List<DocumentoResponseFile> documentos;

    public ArtigoResponseFile(long id, String name, int num, List<DocumentoResponseFile> documentos) {
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

    public List<DocumentoResponseFile> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoResponseFile> documentos) {
        this.documentos = documentos;
    }

}