package com.bombeiros.siteinterno.message;

import org.springframework.web.multipart.MultipartFile;

public class TesteResponseFile {
    private MultipartFile file;

    public TesteResponseFile(){
    }

    public TesteResponseFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return this.file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}