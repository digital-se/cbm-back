package com.digitalse.cbm.back.documento;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.controllers.DocumentoController;
import com.digitalse.cbm.back.services.DocumentoService;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

/*  */
public class BuscarDocumentoTests {
    @InjectMocks
    private DocumentoController documentoController_mock = new DocumentoController();

    @Mock
    private DocumentoService documentoService_mock;
    
    @Before
    public void setup(){
        documentoController_mock = new DocumentoController(documentoService_mock);
    }

    /* @Given("Crio um {documento} novo")
    public void blah1(DocumentoDTO doc){

        //assertTrue(doc != null);
    }
    @When("")
    public void blah2(Object o){
        
        throw new io.cucumber.java.PendingException();
    }
    @Then("")
    public void blah3(Object o){
        
        throw new io.cucumber.java.PendingException();
    } */
}
