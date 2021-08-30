package com.digitalse.cbm.back.repository;

import com.digitalse.cbm.back.entities.Militar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitarRepository extends JpaRepository<Militar, Long> {

    Militar findByMatricula(String matricula);

    boolean existsByMatricula(String matricula);
}
