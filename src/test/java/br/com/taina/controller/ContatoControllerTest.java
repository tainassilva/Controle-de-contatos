package br.com.taina.controller;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.service.ContatoService;
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

public class ContatoControllerTest {

    @Mock
    private ContatoService contatoService; // Simula o serviço de contatos

    @InjectMocks
    private ContatoController contatoController; // Injeta o mock no controlador

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
        // Configuração do MockMvc para simular chamadas HTTP ao controlador
        mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
        objectMapper = new ObjectMapper();
    }

    // Mocando os dados para testar 
    private ContatoDTO mockContato() {
        return new ContatoDTO(1L, "CELULAR", "11974510719", 1L);
    }

    @Test
    public void deveSalvarContatoERetornarNoBody() throws Exception {
        ContatoDTO contatoDTO = mockContato();
        when(contatoService.save(any(ContatoDTO.class))).thenReturn(contatoDTO);

        // Simula uma requisição POST para salvar um contato
        mockMvc.perform(post("/api/contatos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contatoDTO)))
                // Resultados esperados ao injetar o mock
                .andExpect(status().isCreated()) // Verifica se o status é 201 Created
                .andExpect(jsonPath("$.tipoContato").value("CELULAR")) // Confirma o tipo do contato
                .andExpect(jsonPath("$.contato").value("11974510719")) // Confirma o número de contato
                .andExpect(jsonPath("$.idPessoa").value(1)); // Confirma o ID da pessoa
    }

    @Test
    public void deveRetornarContatoPorId() throws Exception {
        ContatoDTO contatoDTO = mockContato();
        when(contatoService.findById(1L)).thenReturn(contatoDTO);

        mockMvc.perform(get("/api/contatos/1"))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.tipoContato").value("CELULAR"))
                .andExpect(jsonPath("$.contato").value("11974510719"));
    }

    @Test
    public void deveListarContatosPorPessoaId() throws Exception {
        ContatoDTO contatoDTO = mockContato();
        when(contatoService.findAllByPessoaId(1L)).thenReturn(List.of(contatoDTO));

        mockMvc.perform(get("/api/contatos/pessoa/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoContato").value("CELULAR"))
                .andExpect(jsonPath("$[0].contato").value("11974510719"));
    }

    @Test
    public void deveAtualizarContato() throws Exception {
        ContatoDTO contatoDTO = mockContato();
        when(contatoService.update(eq(1L), any(ContatoDTO.class))).thenReturn(contatoDTO);

        mockMvc.perform(put("/api/contatos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contatoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoContato").value("CELULAR"))
                .andExpect(jsonPath("$.contato").value("11974510719"));
    }

    @Test
    public void deveDeletarContato() throws Exception {
        doNothing().when(contatoService).delete(1L);

        mockMvc.perform(delete("/api/contatos/1"))
                .andExpect(status().isNoContent()); 
    }
}
