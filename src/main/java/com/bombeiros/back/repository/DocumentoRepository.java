package com.bombeiros.back.repository;

import com.bombeiros.back.models.Documento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
