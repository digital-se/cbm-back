package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Imagem;
import com.bombeiros.siteinterno.models.RelatorioDeProcesso;
import com.bombeiros.siteinterno.repository.ImagemRepository;
import com.bombeiros.siteinterno.repository.RelatorioDeProcessoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RelatorioDeProcessoSaveImageService {

    @Autowired
    ImagemRepository imagemRepository;

    @Autowired
    RelatorioDeProcessoRepository relatorioRepository;

    @Transactional
    public Imagem salvar(RelatorioDeProcesso relatorioProcesso, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Imagem Imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

        relatorioRepository.save(relatorioProcesso);

        Imagem.setRelatorioDeProcesso(relatorioProcesso);

        return imagemRepository.save(Imagem);
    }

    public Imagem getImagem(Long id) {
        return imagemRepository.findById(id).get();
    }

    public Stream<Imagem> getAllImagens() {
        return imagemRepository.findAll().stream();
    }
}
