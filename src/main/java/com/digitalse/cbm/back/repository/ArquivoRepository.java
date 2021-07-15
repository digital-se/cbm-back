package com.digitalse.cbm.back.repository;

import java.util.Optional;

import com.digitalse.cbm.back.entities.Arquivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    /* @Query(value = "SELECT id, nome, ocr, status, mime, tamanho, criado, atualizado FROM arquivos WHERE id = ?1", nativeQuery = true)
    public Optional<Arquivo> findByIdWithoutDados(long id); */

    @Query(value = "SELECT a.id, a.nome, a.ocr, a.status, a.mime, a.tamanho, a.criado, a.atualizado FROM Arquivo a WHERE a.id = ?1")
    public Optional<Arquivo> findByIdWithoutDados(long id_query);
}