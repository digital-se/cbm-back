package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Imagem;
import com.bombeiros.siteinterno.models.RegistroAntigo;
import com.bombeiros.siteinterno.repository.ImagemRepository;
import com.bombeiros.siteinterno.repository.RegistroAntigoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistroAntigoSaveImageService {

    @Autowired
    RegistroAntigoRepository registroAntigoRepository;

    @Autowired
    ImagemRepository imagemRepository;

    @Transactional
    public Imagem salvar(RegistroAntigo registroAntigo, MultipartFile file) throws IOException {
        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Imagem Imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

        registroAntigoRepository.save(registroAntigo);

        Imagem.setRegistroAntigo(registroAntigo);

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
