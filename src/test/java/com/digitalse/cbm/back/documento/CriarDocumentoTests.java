package com.digitalse.cbm.back.documento;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Date;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.controllers.DocumentoController;
import com.digitalse.cbm.back.responseFiles.RFDocumento;
import com.digitalse.cbm.back.services.DocumentoService;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

class CriarDocumentoTests {
    static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}

public class StepDef {



    @InjectMocks
    private DocumentoController documentoController_mock = new DocumentoController();

    @Mock
    private DocumentoService documentoService_mock;

    DocumentoDTO dtoDoc;

    Date data;

    /*
     * Given usuario cria uma request para salvar um documento When usuario envia o
     * documento pelo endpoint Then devo retornar 200
     */

    @Before
    public void setup() {
        documentoController_mock = new DocumentoController(documentoService_mock);
        dtoDoc = new DocumentoDTO();
        data = new Date();
    }

    @Given("usuario cria um documento")
    public void criarDocumento() {
        dtoDoc.setTipo("bga");
        dtoDoc.setNome("nome");
        dtoDoc.setDescricao("descricao");
        dtoDoc.setPublico(false);
        dtoDoc.setNumeracao("221313");
        dtoDoc.setData(data);
    }

    @When("usuario envia o {documento} pelo endpoint")
    public RFDocumento usuario_envia_o_pelo_endpoint(DocumentoDTO dtoDoc) throws IOException {
        return documentoController_mock.cadastrar(dtoDoc).getBody();
        
    }

    @Then("devo retornar o \\{documento} corretamente")
    public void verificaSeIguais(RFDocumento rfDoc) {
        assertAll("heading", 
                () -> assertEquals(rfDoc.getTipo(), dtoDoc.getTipo()),
                () -> assertEquals(rfDoc.getNome(), dtoDoc.getNome()),
                () -> assertEquals(rfDoc.getDescricao(), dtoDoc.getDescricao()),
                () -> assertEquals(rfDoc.getPublico(), dtoDoc.getPublico()),
                () -> assertEquals(rfDoc.getNumeracao(), dtoDoc.getNumeracao()),
                () -> assertEquals(rfDoc.getData(), dtoDoc.getData()));
    }

}
