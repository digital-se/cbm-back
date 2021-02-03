package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.FichaFuncionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaFuncionarioRepository extends JpaRepository<FichaFuncionario, Long> {
    
}
