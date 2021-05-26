package com.bombeiros.siteinterno.serviceTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.BgaDTO;
import com.bombeiros.siteinterno.services.BgaServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class bgaServicesTests {
    
    @Mock
    private BgaServices bgaServices;

    @BeforeEach
    public void setUp(){
        //if inner service, do something here

        //caseDocumentos SetUp
        List<DocumentoDTO> listaDocs = new ArrayList<DocumentoDTO>();
        listaDocs.add(new DocumentoDTO(0L, "sopa 0", 0, null));
        listaDocs.add(new DocumentoDTO(1L, "sopa 1", 1, null));
        listaDocs.add(new DocumentoDTO(2L, "sopa 2", 2, null));
        when(bgaServices.getDocumentos(0L)).thenReturn(listaDocs);

        //caseArtigos SetUp
        List<BgaDTO> lista = new ArrayList<BgaDTO>();
        lista.add(new BgaDTO(0L, "nome", 2111));
        when(bgaServices.getArtigos()).thenReturn(lista);
    }

    @Test
	void caseDocumentos() throws Exception {
		List<DocumentoDTO> returnList = bgaServices.getDocumentos(0L);

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}

    @Test
	void caseArtigos() throws Exception {
		List<BgaDTO> returnList = bgaServices.getArtigos();

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}
}
