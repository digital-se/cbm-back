package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.DTO.ArquivoDTO;
import com.bombeiros.siteinterno.DTO.DocumentoDTO;

import org.springframework.web.multipart.MultipartFile;

public interface IServices {
    
    public ArquivoDTO salvar(DocumentoDTO artigo, MultipartFile file) throws IOException;
    public List<ArquivoDTO> getDocumentos(Long id) throws IOException;
    public List<DocumentoDTO> getArtigos() throws IOException;

}
