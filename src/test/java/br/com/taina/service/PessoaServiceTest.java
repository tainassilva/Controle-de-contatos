package br.com.taina.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.dto.PessoaMalaDiretaDTO;
import br.com.taina.enums.Estados;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.exception.CampoNotNullException;
import br.com.taina.exception.NadaParaListarException;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.PessoaValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaValidation pessoaValidation;

    @InjectMocks
    private PessoaService pessoaService;

    private PessoaDTO pessoaDTO;
    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pessoaDTO = new PessoaDTO(1L, "Nome", "Endereco", "12345-678", "Cidade", "SP");

        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome");
        pessoa.setEndereco("Endereco");
        pessoa.setCep("12345-678");
        pessoa.setCidade("Cidade");
        pessoa.setUf(Estados.SP);
    }

    @Test
    void salvar_DeveSalvarPessoa_QuandoPessoaValida() {
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);

        assertNotNull(pessoaSalva);
        assertEquals("Nome", pessoaSalva.getNome());
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void salvar_DeveLancarIdNotNullException_QuandoIdPessoaForNulo() {
        PessoaDTO pessoaDTOComIdNulo = new PessoaDTO(null, "Nome", "Endereco", "12345-678", "Cidade", "SP");

        assertThrows(CampoNotNullException.class, () -> pessoaService.save(pessoaDTOComIdNulo));
    }


    @Test
    void findAll_DeveRetornarListaDePessoas_QuandoExistiremRegistros() {
        List<Pessoa> pessoas = Arrays.asList(pessoa);
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<PessoaDTO> lista = pessoaService.findAll();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
    }

    @Test
    void findAll_DeveLancarNadaParaListarException_QuandoNaoExistiremPessoas() {
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(NadaParaListarException.class, () -> pessoaService.findAll());
    }

    @Test
    void findById_DeveRetornarPessoaDTO_QuandoPessoaExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaDTO pessoaEncontrada = pessoaService.findById(1L);

        assertNotNull(pessoaEncontrada);
        assertEquals("Nome", pessoaEncontrada.getNome());
    }

    @Test
    void findById_DeveLancarIdNotFoundException_QuandoPessoaNaoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> pessoaService.findById(1L));
    }

    @Test
    void findPessoaById_DeveRetornarPessoaMalaDiretaDTO_QuandoPessoaExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaMalaDiretaDTO pessoaMalaDireta = pessoaService.findPessoaById(1L);

        assertNotNull(pessoaMalaDireta);
        assertEquals("Nome", pessoaMalaDireta.nome());
        assertEquals("Endereco – CEP: 12345-678 – Cidade / SP", pessoaMalaDireta.malaDireta());
    }

    @Test
    void findPessoaById_DeveLancarIdNotFoundException_QuandoPessoaNaoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> pessoaService.findPessoaById(1L));
    }

    @Test
    void update_DeveAtualizarPessoa_QuandoPessoaExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO pessoaAtualizada = pessoaService.update(1L, pessoaDTO);

        assertNotNull(pessoaAtualizada);
        assertEquals("Nome", pessoaAtualizada.getNome());
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void update_DeveLancarIdNotFoundException_QuandoPessoaNaoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> pessoaService.update(1L, pessoaDTO));
    }

    @Test
    void delete_DeveDeletarPessoa_QuandoPessoaExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        pessoaService.delete(1L);

        verify(pessoaRepository, times(1)).delete(any(Pessoa.class));
    }

    @Test
    void delete_DeveLancarIdNotFoundException_QuandoPessoaNaoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> pessoaService.delete(1L));
    }
}
