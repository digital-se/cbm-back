package com.bombeiros.siteinterno.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.BgaDTO;
import com.bombeiros.siteinterno.controllers.BgaController;
import com.bombeiros.siteinterno.models.Bga;
import com.bombeiros.siteinterno.models.Arquivo;
import com.bombeiros.siteinterno.services.BgaServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
@AutoConfigureMockMvc
public class bgaTest {
 
    private BgaController bgaController;

    @Mock
    private BgaServices bgaServices;

    // @Autowired
	// private MockMvc mockMvc;

	// @Autowired
	// private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        bgaServices = Mockito.mock(BgaServices.class);
        bgaController = new BgaController(bgaServices);
      }
    
    
    @Test
	void caseSalvar() throws Exception {
        Bga bga = new Bga("a", 123);
        MockMultipartFile mmf = new MockMultipartFile("file", new byte[0]);
        when(bgaServices.salvar(null, null)).thenReturn(new Arquivo("file", "jpg", new byte[0]));
        assertEquals(HttpStatus.OK, bgaController.salvar(bga, mmf).getStatusCode());
        verify(bgaServices).salvar(bga, mmf);
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
    

        when(bgaServices.getDocumentos(0L)).thenReturn(lista);
        assertEquals(HttpStatus.OK, bgaController.listarDocumentos(0L).getStatusCode());
        
		// verify(bgaServices).getArtigos();
	}

    @Test
	void caseArtigos() throws Exception {
		List<BgaDTO> lista = new ArrayList<BgaDTO>();
        lista.add(new BgaDTO(0L, "nome", 2111));

        when(bgaServices.getArtigos()).thenReturn(lista);
        assertEquals(HttpStatus.OK, bgaController.listarArtigos().getStatusCode());
        
		verify(bgaServices).getArtigos();
	}
}
