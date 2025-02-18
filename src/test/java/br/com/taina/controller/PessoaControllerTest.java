package br.com.taina.controller;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSavePessoa() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO(1L, "Taina", "Rua Penha, 102", "06700000", "Cotia", "SP");
        when(pessoaService.save(any(PessoaDTO.class))).thenReturn(pessoaDTO);

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Taina"))
                .andExpect(jsonPath("$.endereco").value("Rua Penha, 102"))
                .andExpect(jsonPath("$.cep").value("06700000"))
                .andExpect(jsonPath("$.cidade").value("Cotia"))
                .andExpect(jsonPath("$.uf").value("SP"));
    }

    @Test
    public void testFindAll() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO(1L, "Taina", "Rua Penha, 102", "06700000", "Cotia", "SP");
        when(pessoaService.findAll()).thenReturn(List.of(pessoaDTO));

        mockMvc.perform(get("/api/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Taina"))
                .andExpect(jsonPath("$[0].cidade").value("Cotia"));
    }

    @Test
    public void testFindById() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO(1L, "Taina", "Rua Penha, 102", "06700000", "Cotia", "SP");
        when(pessoaService.findById(1L)).thenReturn(pessoaDTO);

        mockMvc.perform(get("/api/pessoas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Taina"))
                .andExpect(jsonPath("$.endereco").value("Rua Penha, 102"));
    }

    @Test
    public void testDeletePessoa() throws Exception {
        doNothing().when(pessoaService).delete(1L);

        mockMvc.perform(delete("/api/pessoas/1"))
                .andExpect(status().isNoContent());
    }
}
