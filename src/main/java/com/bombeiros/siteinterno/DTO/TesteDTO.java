package com.bombeiros.siteinterno.DTO;

public class TesteDTO {
    private byte[] file;

    public TesteDTO(){
    }

    public TesteDTO(byte[] file) {
        this.file = file;
    }

    public byte[] getFile() {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}