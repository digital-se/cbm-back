package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.models.Imagem;
import com.bombeiros.siteinterno.repository.BirRepository;
import com.bombeiros.siteinterno.repository.ImagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BirSaveImageService {
    
    @Autowired
    BirRepository birRepository;

    @Autowired
    ImagemRepository imagemRepository;

    @Transactional
    public Imagem salvar(Bir bir, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Imagem Imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

        birRepository.save(bir);

        Imagem.setBir(bir);

        return imagemRepository.save(Imagem);
}

    public Imagem getImagem(Long id) {
        return imagemRepository.findById(id).get();
    }

    public Stream<Imagem> getAllImagens() {
        return imagemRepository.findAll().stream();
    }
}
