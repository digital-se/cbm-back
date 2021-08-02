Feature: Criar documento

    Documento Criado

    Scenario: Documento está correto
        Given o documento está correto
        When tento criar o documento
        Then devo retornar o documento
    
    Scenario: Documento está incorreto
        Given o documento está incorreto
        When tento criar o documento
        Then devo retornar um erro