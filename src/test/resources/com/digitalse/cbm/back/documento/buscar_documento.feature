Feature: Buscar documento

    Tentar encontrar um documento no banco de dados

    Scenario: Pelo menos um documento existe
        Given documento que existe
        When tento buscar um documento
        Then devo retornar o/os documento
    
    Scenario: Documento não existe
        Given documento que não existe
        When tento buscar um documento
        Then devo retornar um erro dizendo que não foi encontrado