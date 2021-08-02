Feature: Listar arquivos de um documento

    Tentar listar os arquivos de um documento

    Scenario: Documento existe
        Given documento que existe
        When tento listar os arquivos do documento
        Then devo retornar uma lista de arquivos
    
    Scenario: Documento não existe
        Given documento que não existe
        When tento listar os arquivos do documento
        Then devo retornar um erro