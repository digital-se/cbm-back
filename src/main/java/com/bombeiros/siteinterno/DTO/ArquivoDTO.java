package com.bombeiros.siteinterno.DTO;

public class ArquivoDTO {

    private long id;
    private String name;
    private String url;
    private String type;
    private long size;
    
    //VERIFICAR COM O MODEL
    public ArquivoDTO(Long id, String name, String url, String type, long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
