package com.digitalse.cbm.back.repository;

import java.util.Optional;

import com.digitalse.cbm.back.entities.Militar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitarRepository extends JpaRepository<Militar, Long> {

    Optional<Militar> findByMatricula(String matricula);

    boolean existsByMatricula(String matricula);
}
