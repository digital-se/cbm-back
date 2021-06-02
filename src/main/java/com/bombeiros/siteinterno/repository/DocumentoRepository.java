package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.Documento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
