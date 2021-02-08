package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.models.RelatorioDeProcesso;
import com.bombeiros.siteinterno.repository.DocumentoRepository;
import com.bombeiros.siteinterno.repository.RelatorioDeProcessoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RelatorioDeProcessoSaveDocumentoService {

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    RelatorioDeProcessoRepository relatorioRepository;

    @Transactional
    public Documento salvar(RelatorioDeProcesso relatorioProcesso, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        relatorioRepository.save(relatorioProcesso);

        documento.setRelatorioDeProcesso(relatorioProcesso);

        return documentoRepository.save(documento);
    }

    public Documento getDocumento(Long id) {
        return documentoRepository.findById(id).get();
    }

    public Stream<Documento> getAllDocumentos() {
        return documentoRepository.findAll().stream();
    }
}
