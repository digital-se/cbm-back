package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.RegistroAntigo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroAntigoRepository extends JpaRepository<RegistroAntigo, Long> {
    
}
