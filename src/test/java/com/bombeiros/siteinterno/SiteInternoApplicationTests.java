package com.bombeiros.siteinterno;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@AutoConfigureMockMvc
class SiteInternoApplicationTests {

	// @Autowired
	// private MockMvc mockMvc;

	// @Autowired
	// private ObjectMapper objectMapper;

	// @Test
	// @Disabled
    // void contextLoads() {
    // }

	// // Faz o teste para verificar se o upload de imagens está realmente funcionando.
	// @Test
	// void case2() throws Exception {
		
	// 		Documento imagem = new Documento();
	// 		mockMvc.perform(post("/imagens")
	// 		.contentType("application/json")
	// 		.content(objectMapper.writeValueAsString(imagem)))
	// 		.andExpect(status().isOk());
	// }

	// // Faz o teste para verificar se a listagem de imagens está realmente funcionando.
	// @Test
	// void case3() throws Exception {
	// 	try {
	// 		Documento imagem = new Documento();
	// 		mockMvc.perform(get("/imagens")
	// 		.contentType("application/json")
	// 		.content(objectMapper.writeValueAsString(imagem)))
	// 		.andExpect(status().isOk());
	// 	} catch (Exception e) {
	// 		System.out.println(e.getMessage());
	// 	}
	// }

}
