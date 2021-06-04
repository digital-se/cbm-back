package com.bombeiros.back.repository;

import com.bombeiros.back.models.Arquivo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
