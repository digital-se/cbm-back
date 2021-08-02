Feature: Deletar documento

    Documento Deletado

    Scenario: Documento existe
        Given documento que existe
        When tento deletar o documento
        Then devo retornar um http status para sucesso
    
    Scenario: Documento não existe
        Given documento que não existe
        When tento deletar o documento
        Then devo retornar um http status de erro