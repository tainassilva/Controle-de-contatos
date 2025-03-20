package br.com.taina.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.taina.exception.IdNotFoundException;
import br.com.taina.dto.pessoa.PessoaDTO;
import br.com.taina.enums.Estados;
import br.com.taina.exception.CampoNotNullException;
import br.com.taina.exception.NadaParaListarException;
import br.com.taina.entity.Pessoa;
import br.com.taina.repository.PessoaRepository;
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

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome");
        pessoa.setEndereco("Endereco");
        pessoa.setCep("12345-678");
        pessoa.setCidade("Cidade");
        pessoa.setUf(Estados.SP);  
    }

    @Test
    void deveSalvarPessoaQuandoPessoaForValida() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setEndereco(pessoa.getEndereco());
        pessoaDTO.setCep(pessoa.getCep());
        pessoaDTO.setCidade(pessoa.getCidade());
        
        pessoaDTO.setUf(pessoa.getUf().name());  // .name() transforma o enum em String

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);

        assertNotNull(pessoaSalva);
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void deveLancarIdNotNullExceptionQuandoIdPessoaForNulo() {
        pessoa.setId(null);

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setEndereco(pessoa.getEndereco());
        pessoaDTO.setCep(pessoa.getCep());
        pessoaDTO.setCidade(pessoa.getCidade());

        pessoaDTO.setUf(pessoa.getUf().name());  

        assertThrows(CampoNotNullException.class, () -> pessoaService.save(pessoaDTO));
    }

    @Test
    void deveRetornarListaDePessoasQuandoExistiremPessoas() {
        List<Pessoa> pessoas = Arrays.asList(pessoa);
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<PessoaDTO> lista = pessoaService.findAll();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    void deveLancarNadaParaListarExceptionQuandoNaoExistiremPessoas() {
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(NadaParaListarException.class, () -> pessoaService.findAll());
    }

    @Test
    void deveRetornarPessoaQuandoPessoaExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaDTO pessoaEncontrada = pessoaService.findById(1L);

        assertNotNull(pessoaEncontrada);
    }

    @Test
    void deveLancarIdNotFoundExceptionQuandoPessoaNaoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> pessoaService.findById(1L));
    }

    @Test
    void deveAtualizarPessoaQuandoPessoaExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setEndereco(pessoa.getEndereco());
        pessoaDTO.setCep(pessoa.getCep());
        pessoaDTO.setCidade(pessoa.getCidade());

        pessoaDTO.setUf(pessoa.getUf().name());  

        PessoaDTO pessoaAtualizada = pessoaService.update(1L, pessoaDTO);

        assertNotNull(pessoaAtualizada);
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void deveDeletarPessoaQuandoPessoaExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        pessoaService.delete(1L);

        verify(pessoaRepository, times(1)).delete(any(Pessoa.class));
    }

    @Test
    void deveLancarIdNotFoundException_QuandoPessoaNaoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> pessoaService.delete(1L));
    }
}
