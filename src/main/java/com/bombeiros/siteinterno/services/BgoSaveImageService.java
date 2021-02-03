package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Bgo;
import com.bombeiros.siteinterno.models.Imagem;
import com.bombeiros.siteinterno.repository.BgoRepository;
import com.bombeiros.siteinterno.repository.ImagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BgoSaveImageService {

    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private BgoRepository bgoRepository;

    @Transactional
    public Imagem salvar(Bgo bgo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Imagem Imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

        bgoRepository.save(bgo);

        Imagem.setBgo(bgo);

        return imagemRepository.save(Imagem);
    }

     // Método de retornar uma imagem pelo seu ID
     public Imagem getImagem(Long id) {
        return imagemRepository.findById(id).get();
    }

    // Método de retornar todas as imagens
    public Stream<Imagem> getAllImagens() {
        return imagemRepository.findAll().stream();
    }
}
