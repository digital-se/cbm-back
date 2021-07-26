package com.digitalse.cbm.back.repository;

import com.digitalse.cbm.back.entities.Arquivo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    
}