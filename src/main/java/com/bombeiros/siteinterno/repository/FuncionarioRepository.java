package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>  {
    
}
