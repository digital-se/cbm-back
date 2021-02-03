package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.Bir;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BirRepository extends JpaRepository<Bir, Long> {
    
}
