package com.digitalse.cbm.back.repository;

import java.util.List;
import java.util.Optional;

import com.digitalse.cbm.back.entities.Arquivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    
    @Query("SELECT a.id FROM Arquivo a WHERE a.ocr = true AND a.status = \'Em pre-processamento\' ORDER BY a.criado ASC")
    Optional<List<Long>> findOrderingCriado();
}