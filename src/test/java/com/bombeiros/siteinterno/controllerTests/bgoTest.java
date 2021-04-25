package com.bombeiros.siteinterno.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.controllers.BgoController;
import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.BgoResponseFile;
import com.bombeiros.siteinterno.models.Bgo;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.services.BgoServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

public class bgoTest {
    
    private BgoController bgoController;

    @Mock
    private BgoServices bgoServices;

    // @Autowired
	// private MockMvc mockMvc;

	// @Autowired
	// private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        bgoServices = Mockito.mock(BgoServices.class);
        bgoController = new BgoController(bgoServices);
      }
    
    
    @Test
	void caseSalvar() throws Exception {
        Bgo bgo = new Bgo("a", 123);
        MockMultipartFile mmf = new MockMultipartFile("file", new byte[0]);
        when(bgoServices.salvar(null, null)).thenReturn(new Documento("file", "jpg", new byte[0]));
        assertEquals(HttpStatus.OK, bgoController.salvar(bgo, mmf).getStatusCode());
        verify(bgoServices).salvar(bgo, mmf);
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
    

        when(bgoServices.getDocumentos(0L)).thenReturn(lista);
        assertEquals(HttpStatus.OK, bgoController.listarDocumentos(0L).getStatusCode());
        
		// verify(bgaServices).getArtigos();
	}

    @Test
	void caseArtigos() throws Exception {
		List<BgoResponseFile> lista = new ArrayList<BgoResponseFile>();
        lista.add(new BgoResponseFile(0L, "nome", 2111));

        when(bgoServices.getArtigos()).thenReturn(lista);
        assertEquals(HttpStatus.OK, bgoController.listarArtigos().getStatusCode());
        
		verify(bgoServices).getArtigos();
	}
}

