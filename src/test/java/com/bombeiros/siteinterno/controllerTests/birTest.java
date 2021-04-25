package com.bombeiros.siteinterno.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.controllers.BirController;
import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.BirResponseFile;
import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.services.BirServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

public class birTest {
    
    private BirController birController;

    @Mock
    private BirServices birServices;

    // @Autowired
	// private MockMvc mockMvc;

	// @Autowired
	// private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        birServices = Mockito.mock(BirServices.class);
        birController = new BirController(birServices);
      }
    
    
    @Test
	void caseSalvar() throws Exception {
        Bir bir = new Bir("a", 123);
        MockMultipartFile mmf = new MockMultipartFile("file", new byte[0]);
        when(birServices.salvar(null, null)).thenReturn(new Documento("file", "jpg", new byte[0]));
        assertEquals(HttpStatus.OK, birController.salvar(bir, mmf).getStatusCode());
        verify(birServices).salvar(bir, mmf);
			// Documento imagem = new Documento();
			// mockMvc.perform(post("/bga/salvar")
			// .contentType("application/json")
			// .content(objectMapper.writeValueAsString(imagem)))
			// .andExpect(status().isOk());
	}

    @Test
	void caseDocumentos() throws Exception {
		List<ArtigoResponseFile> lista = new ArrayList<ArtigoResponseFile>();
        lista.add(new ArtigoResponseFile(0L, "sopa 0", 0, null));
        lista.add(new ArtigoResponseFile(1L, "sopa 1", 1, null));
        lista.add(new ArtigoResponseFile(2L, "sopa 2", 2, null));
    

        when(birServices.getDocumentos(0L)).thenReturn(lista);
        assertEquals(HttpStatus.OK, birController.listarDocumentos(0L).getStatusCode());
        
		// verify(bgaServices).getArtigos();
	}

    @Test
	void caseArtigos() throws Exception {
		List<BirResponseFile> lista = new ArrayList<BirResponseFile>();
        lista.add(new BirResponseFile(0L, "nome", 2111));

        when(birServices.getArtigos()).thenReturn(lista);
        assertEquals(HttpStatus.OK, birController.listarArtigos().getStatusCode());
        
		verify(birServices).getArtigos();
	}
}

