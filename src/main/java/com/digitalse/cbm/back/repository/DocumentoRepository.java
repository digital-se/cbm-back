package com.digitalse.cbm.back.repository;

import com.digitalse.cbm.back.entities.Documento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
