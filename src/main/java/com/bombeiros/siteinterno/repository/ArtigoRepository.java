package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.Arquivo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository extends JpaRepository<Arquivo, Long> {
    
}
