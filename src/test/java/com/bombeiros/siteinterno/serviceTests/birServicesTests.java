package com.bombeiros.siteinterno.serviceTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.BirDTO;
import com.bombeiros.siteinterno.services.BirServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class birServicesTests {
    
    @Mock
    private BirServices birServices;

    @BeforeEach
    public void setUp(){
        //if inner service, do something here

        //caseDocumentos SetUp
        List<DocumentoDTO> listaDocs = new ArrayList<DocumentoDTO>();
        listaDocs.add(new DocumentoDTO(0L, "sopa 0", 0, null));
        listaDocs.add(new DocumentoDTO(1L, "sopa 1", 1, null));
        listaDocs.add(new DocumentoDTO(2L, "sopa 2", 2, null));
        when(birServices.getDocumentos(0L)).thenReturn(listaDocs);

        //caseArtigos SetUp
        List<BirDTO> lista = new ArrayList<BirDTO>();
        lista.add(new BirDTO(0L, "nome", 2111));
        when(birServices.getArtigos()).thenReturn(lista);
    }

    @Test
	void caseDocumentos() throws Exception {
		List<DocumentoDTO> returnList = birServices.getDocumentos(0L);

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}

    @Test
	void caseArtigos() throws Exception {
		List<BirDTO> returnList = birServices.getArtigos();

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}
}

