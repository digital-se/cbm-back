package com.bombeiros.siteinterno.serviceTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.RelatorioProcessoResponseFile;
import com.bombeiros.siteinterno.services.RelatorioDeProcessoServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class relatorioProcessoServicesTests {
    
    @Mock
    private RelatorioDeProcessoServices services;

    @BeforeEach
    public void setUp(){
        //if inner service, do something here

        //caseDocumentos SetUp
        List<ArtigoResponseFile> listaDocs = new ArrayList<ArtigoResponseFile>();
        listaDocs.add(new ArtigoResponseFile(0L, "sopa 0", 0, null));
        listaDocs.add(new ArtigoResponseFile(1L, "sopa 1", 1, null));
        listaDocs.add(new ArtigoResponseFile(2L, "sopa 2", 2, null));
        when(services.getDocumentos(0L)).thenReturn(listaDocs);

        //caseArtigos SetUp
        List<RelatorioProcessoResponseFile> lista = new ArrayList<RelatorioProcessoResponseFile>();
        lista.add(new RelatorioProcessoResponseFile(0L, 0, null));
        when(services.getArtigos()).thenReturn(lista);
    }

    @Test
	void caseDocumentos() throws Exception {
		List<ArtigoResponseFile> returnList = services.getDocumentos(0L);

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}

    @Test
	void caseArtigos() throws Exception {
		List<RelatorioProcessoResponseFile> returnList = services.getArtigos();

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}
}

