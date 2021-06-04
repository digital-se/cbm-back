package com.bombeiros.back.repository;

import com.bombeiros.back.models.Militar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitarRepository extends JpaRepository<Militar, Long> {

    Militar findByNumMatricula(String numMatricula);
}
