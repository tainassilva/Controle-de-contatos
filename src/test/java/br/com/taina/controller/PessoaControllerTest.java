package br.com.taina.controller;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService; 

    @InjectMocks
    private PessoaController pessoaController; 

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
        // Configuração do MockMvc para simular chamadas HTTP ao controlador
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
        objectMapper = new ObjectMapper();
    }

    // Mocando os dados para testar
    private PessoaDTO mockPessoa() {
       // return new PessoaDTO(1L, "Taina", "Rua Penha, 102", "06700000", "Cotia", "SP");
    }

    @Test
    public void deveSalvarPessoaERetornarNoBody() throws Exception {
        PessoaDTO pessoaDTO = mockPessoa();
        when(pessoaService.save(any(PessoaDTO.class))).thenReturn(pessoaDTO);

        // Simula uma requisição POST para salvar uma pessoa
        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoaDTO)))
                // Resultados esperados ao injetar o mock
                .andExpect(status().isCreated()) // Verifica se o status é 201 Created
                .andExpect(jsonPath("$.nome").value("Taina")) // Confirma o nome da pessoa
                .andExpect(jsonPath("$.endereco").value("Rua Penha, 102")) // Confirma o endereço
                .andExpect(jsonPath("$.cep").value("06700000")) // Confirma o CEP
                .andExpect(jsonPath("$.cidade").value("Cotia")) // Confirma a cidade
                .andExpect(jsonPath("$.uf").value("SP")); // Confirma o estado
    }

    @Test
    public void deveRetornarPessoaPorId() throws Exception {
        PessoaDTO pessoaDTO = mockPessoa();
        when(pessoaService.findById(1L)).thenReturn(pessoaDTO);

        mockMvc.perform(get("/api/pessoas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Taina"))
                .andExpect(jsonPath("$.endereco").value("Rua Penha, 102"))
                .andExpect(jsonPath("$.cep").value("06700000"));
    }

    @Test
    public void deveListarPessoas() throws Exception {
        PessoaDTO pessoaDTO = mockPessoa();
        when(pessoaService.findAll()).thenReturn(List.of(pessoaDTO));

        mockMvc.perform(get("/api/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Taina"))
                .andExpect(jsonPath("$[0].endereco").value("Rua Penha, 102"))
                .andExpect(jsonPath("$[0].cep").value("06700000"))
                .andExpect(jsonPath("$[0].uf").value("SP"));
    }

    @Test
    public void deveAtualizarPessoa() throws Exception {
        PessoaDTO pessoaDTO = mockPessoa();
        when(pessoaService.update(eq(1L), any(PessoaDTO.class))).thenReturn(pessoaDTO);

        mockMvc.perform(put("/api/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Taina"))
                .andExpect(jsonPath("$.endereco").value("Rua Penha, 102"));
    }

    @Test
    public void deveDeletarPessoa() throws Exception {
        doNothing().when(pessoaService).delete(1L);

        mockMvc.perform(delete("/api/pessoas/1"))
                .andExpect(status().isNoContent());
    }
}
