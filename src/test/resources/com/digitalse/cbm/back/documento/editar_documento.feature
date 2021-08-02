Feature: Editar documento

    Documento Editado

    Scenario: Documento existe
        Given documento que existe
        When tento editar o documento
        Then devo retornar o documento editado
    
    Scenario: Documento não existe
        Given documento que não existe
        When tento editar o documento
        Then devo retornar um http status de erro

    Scenario: Campos null vindos do front existem
        Given documento que existe
        When tento editar o documento com campos incompletos
        Then devo editar apenas campos com valores