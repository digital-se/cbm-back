package com.bombeiros.siteinterno.serviceTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.BgoResponseFile;
import com.bombeiros.siteinterno.services.BgoServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class bgoServicesTests {
    
    @Mock
    private BgoServices bgoServices;

    @BeforeEach
    public void setUp(){
        //if inner service, do something here

        //caseDocumentos SetUp
        List<ArtigoResponseFile> listaDocs = new ArrayList<ArtigoResponseFile>();
        listaDocs.add(new ArtigoResponseFile(0L, "sopa 0", 0, null));
        listaDocs.add(new ArtigoResponseFile(1L, "sopa 1", 1, null));
        listaDocs.add(new ArtigoResponseFile(2L, "sopa 2", 2, null));
        when(bgoServices.getDocumentos(0L)).thenReturn(listaDocs);

        //caseArtigos SetUp
        List<BgoResponseFile> lista = new ArrayList<BgoResponseFile>();
        lista.add(new BgoResponseFile(0L, "nome", 2111));
        when(bgoServices.getArtigos()).thenReturn(lista);
    }

    @Test
	void caseDocumentos() throws Exception {
		List<ArtigoResponseFile> returnList = bgoServices.getDocumentos(0L);

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}

    @Test
	void caseArtigos() throws Exception {
		List<BgoResponseFile> returnList = bgoServices.getArtigos();

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}
}
