package com.bombeiros.siteinterno.message;

import java.util.List;

public class DocumentResponseFile {

    private long id;
    private String name;
    private int num;
    private List<ResponseFile> imagens;

    public DocumentResponseFile(long id, String name, int num, List<ResponseFile> imagens) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.imagens = imagens;
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

    public List<ResponseFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<ResponseFile> imagens) {
        this.imagens = imagens;
    }

}