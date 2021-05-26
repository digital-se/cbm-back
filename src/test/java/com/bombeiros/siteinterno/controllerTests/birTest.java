package com.bombeiros.siteinterno.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.BirDTO;
import com.bombeiros.siteinterno.controllers.BirController;
import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.models.Arquivo;
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
        when(birServices.salvar(null, null)).thenReturn(new Arquivo("file", "jpg", new byte[0]));
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
		List<DocumentoDTO> lista = new ArrayList<DocumentoDTO>();
        lista.add(new DocumentoDTO(0L, "sopa 0", 0, null));
        lista.add(new DocumentoDTO(1L, "sopa 1", 1, null));
        lista.add(new DocumentoDTO(2L, "sopa 2", 2, null));
    

        when(birServices.getDocumentos(0L)).thenReturn(lista);
        assertEquals(HttpStatus.OK, birController.listarDocumentos(0L).getStatusCode());
        
		// verify(bgaServices).getArtigos();
	}

    @Test
	void caseArtigos() throws Exception {
		List<BirDTO> lista = new ArrayList<BirDTO>();
        lista.add(new BirDTO(0L, "nome", 2111));

        when(birServices.getArtigos()).thenReturn(lista);
        assertEquals(HttpStatus.OK, birController.listarArtigos().getStatusCode());
        
		verify(birServices).getArtigos();
	}
}

