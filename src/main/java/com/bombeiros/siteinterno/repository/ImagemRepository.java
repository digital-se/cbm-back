package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.Imagem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
}