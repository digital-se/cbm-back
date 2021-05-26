package com.bombeiros.siteinterno.serviceTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.FichaFuncionarioDTO;
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
        List<DocumentoDTO> listaDocs = new ArrayList<DocumentoDTO>();
        listaDocs.add(new DocumentoDTO(0L, "sopa 0", 0, null));       
        listaDocs.add(new DocumentoDTO(1L, "sopa 1", 1, null));
        listaDocs.add(new DocumentoDTO(2L, "sopa 2", 2, null));
        when(ffServices.getDocumentos(0L)).thenReturn(listaDocs);

        //caseArtigos SetUp
        List<FichaFuncionarioDTO> lista = new ArrayList<FichaFuncionarioDTO>();
        lista.add(new FichaFuncionarioDTO(1L, "aaaaaa", 0, null,  null, null));
        when(ffServices.getArtigos()).thenReturn(lista);
    }

    @Test
	void caseDocumentos() throws Exception {
		List<DocumentoDTO> returnList = ffServices.getDocumentos(0L);

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}

    @Test
	void caseArtigos() throws Exception {
		List<FichaFuncionarioDTO> returnList = ffServices.getArtigos();

        assertNotNull(returnList);
        //assertTrue(!returnList.isEmpty());        
	}
}

