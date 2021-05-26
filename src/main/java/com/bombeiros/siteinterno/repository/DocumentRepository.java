package com.bombeiros.siteinterno.repository;


import com.bombeiros.siteinterno.models.Document;


import org.springframework.data.jpa.repository.JpaRepository;



public interface DocumentRepository extends JpaRepository<Document, Long> {

    
}
