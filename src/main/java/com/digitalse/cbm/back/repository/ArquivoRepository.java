package com.digitalse.cbm.back.repository;

import java.util.Optional;

import com.digitalse.cbm.back.entities.Arquivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    
    @Query(value = "SELECT new com.digitalse.cbm.back.entities.Arquivo(a.id, a.nome, a.ocr, a.status, a.criado, a.atualizado, a.texto) FROM Arquivo a WHERE a.id = ?1")
    public Optional<Arquivo> findByIdWithoutDados(long id_query);
}