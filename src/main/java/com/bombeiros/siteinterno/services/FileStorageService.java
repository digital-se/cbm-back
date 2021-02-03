package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Bga;
import com.bombeiros.siteinterno.models.Imagem;
import com.bombeiros.siteinterno.repository.BgaRepository;
import com.bombeiros.siteinterno.repository.ImagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    
    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private BgaRepository bgaRepository;


    // Método de salvar as informações do Bga e uma imagem referente ao Bga
    // Dependendo do banco de dados utilizado, provavelmente deverá ser feito alterações no "application.properties" para ser feito o upload de imagens
    // No application.properties você definirá o tamanho máximo que uma imagem pode ter para ser salva no banco de dados
    @Transactional
    public Imagem salvar(Bga bga, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Imagem Imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

        bgaRepository.save(bga);
        Imagem.setBga(bga);

        return imagemRepository.save(Imagem);
    }

    public Imagem salvarImagem(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Imagem Imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

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
