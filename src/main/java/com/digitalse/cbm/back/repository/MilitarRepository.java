package com.digitalse.cbm.back.repository;

import com.digitalse.cbm.back.models.Militar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitarRepository extends JpaRepository<Militar, Long> {

    Militar findByNumMatricula(String numMatricula);
}
