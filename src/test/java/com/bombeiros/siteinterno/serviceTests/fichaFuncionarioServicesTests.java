package com.bombeiros.siteinterno.serviceTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.FichaFuncionarioResponseFile;
import com.bombeiros.siteinterno.services.FichaFuncionarioServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class fichaFuncionarioServicesTests {
    
    @Mock
    private FichaFuncionarioServices ffServices;

    @BeforeEach
    public void setUp(){
        //if inner service, do something here

        //caseDocumentos SetUp
        List<ArtigoResponseFile> listaDocs = new ArrayList<ArtigoResponseFile>();
        listaDocs.add(new ArtigoResponseFile(0L, "sopa 0", 0, null));       
        listaDocs.add(new ArtigoResponseFile(1L, "sopa 1", 1, null));
        listaDocs.add(new ArtigoResponseFile(2L, "sopa 2", 2, null));
        when(ffServices.getDocumentos(0L)).thenReturn(listaDocs);

        //caseArtigos SetUp
        List<FichaFuncionarioResponseFile> lista = new ArrayList<FichaFuncionarioResponseFile>();
        lista.add(new FichaFuncionarioResponseFile(1L, "aaaaaa", 0, null,  null, null));
        when(ffServices.getArtigos()).thenReturn(lista);
    }

    @Test
	void caseDocumentos() throws Exception {
		List<ArtigoResponseFile> returnList = ffServices.getDocumentos(0L);

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}

    @Test
	void caseArtigos() throws Exception {
		List<FichaFuncionarioResponseFile> returnList = ffServices.getArtigos();

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}
}

