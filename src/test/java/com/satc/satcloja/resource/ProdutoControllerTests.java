package com.satc.satcloja.resource;

import com.satc.satcloja.model.Produto;
import com.satc.satcloja.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    public void testCreateProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setValorUnitario(100.00);
        Mockito.when(produtoService.salvar(Mockito.any(Produto.class))).thenReturn(produto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Produto Teste\", \"valorUnitario\": 100.0}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.valorUnitario").value(100.0))
                .andExpect(jsonPath("$.nome").value("Produto Teste"));
    }

    @Test
    public void testFindAllProdutos() throws Exception {
        Page<Produto> produtos = new PageImpl<>(Arrays.asList(new Produto(), new Produto()));
        Mockito.when(produtoService.buscaTodos(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(produtos);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
