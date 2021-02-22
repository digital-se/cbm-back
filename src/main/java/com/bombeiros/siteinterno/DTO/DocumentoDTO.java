package com.bombeiros.siteinterno.DTO;

public class DocumentoDTO {

    private Long idDocumento;

    private String name;

    private String type;

    private byte[] documentoData;

    public DocumentoDTO(Long idDocumento, String name, String type, byte[] documento) {
        this.idDocumento = idDocumento;
        this.name = name;
        this.type = type;
        this.documentoData = documento;
    }

    public Long getId() {
        return idDocumento;
    }

    public void setId(Long idDocumento) {
        this.idDocumento = idDocumento;
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
        return documentoData;
    }

    public void setDocumento(byte[] documento) {
        this.documentoData = documento;
    }
    
    

    
}
