Feature: Adicionar arquivo a documento

    Tentar adicionar um ou mais arquivos a um documento

    Scenario: Documento existe
        Given documento que existe
        When tento adicionar um ou mais arquivos
        Then devo retornar os arquivos do documento após a inserção
    
    Scenario: Documento não existe
        Given documento que não existe
        When tento adicionar um ou mais arquivos
        Then devo retornar um erro