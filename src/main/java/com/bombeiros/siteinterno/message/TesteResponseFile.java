package com.bombeiros.siteinterno.message;

import org.springframework.web.multipart.MultipartFile;

public class TesteResponseFile {
    private byte[] file;

    public TesteResponseFile(){
    }

    public TesteResponseFile(byte[] file) {
        this.file = file;
    }

    public byte[] getFile() {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}