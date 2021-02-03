package com.bombeiros.siteinterno.repository;

import com.bombeiros.siteinterno.models.Bgo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BgoRepository extends JpaRepository<Bgo, Long> {
    
}
