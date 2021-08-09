Feature: Criar documento

    Documento Criado
 
    Scenario: Criar documento corretamente
        Given usuario cria um documento
        When usuario envia o {DocumentoDTO} pelo endpoint
        Then devo retornar o {documento} corretamente
        