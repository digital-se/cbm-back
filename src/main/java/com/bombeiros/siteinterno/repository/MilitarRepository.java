package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.Militar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitarRepository extends JpaRepository<Militar, Long> {

    Militar findByNumMatricula(String numMatricula);
}
