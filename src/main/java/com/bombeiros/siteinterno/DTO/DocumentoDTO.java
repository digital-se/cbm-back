package com.bombeiros.siteinterno.DTO;

public class DocumentoDTO {

    private Long id_documento;

    private String name;

    private String type;

    private byte[] documento;

    public DocumentoDTO(Long id_documento, String name, String type, byte[] documento) {
        this.id_documento = id_documento;
        this.name = name;
        this.type = type;
        this.documento = documento;
    }

    public Long getId_documento() {
        return id_documento;
    }

    public void setId_documento(Long id_documento) {
        this.id_documento = id_documento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }
    
    

    
}
