package br.com.taina.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.enums.TipoContato;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.model.Contato;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.ContatoRepository;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.ContatoValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

class ContatoServiceTest {

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ContatoValidation contatoValidation;

    @InjectMocks
    private ContatoService contatoService;

    private Pessoa pessoa;
    private ContatoDTO contatoDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        pessoa = new Pessoa();
        pessoa.setId(1L);

        contatoDTO = new ContatoDTO();
        contatoDTO.setIdPessoa(1L);
        contatoDTO.setTipoContato("EMAIL");
        contatoDTO.setContato("test@example.com");
    }

    @Test
    void deveSalvarContatoQuandoPessoaExistir() {
        // Simula o comportamento dos métodos dos repositórios
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(contatoRepository.save(any(Contato.class))).thenReturn(new Contato());

        // Executa o método do serviço
        ContatoDTO contatoSalvo = contatoService.save(contatoDTO);

        // Verifica se o contato foi salvo
        assertNotNull(contatoSalvo);
        verify(contatoRepository, times(1)).save(any(Contato.class));
    }

    @Test
    void deveLancarIdNotFoundExceptionQuandoPessoaNaoExistir() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        // Executa o método do serviço e verifica se a exceção é lançada
        assertThrows(IdNotFoundException.class, () -> contatoService.save(contatoDTO));
    }

    @Test
    void deveRetornarContatoDTOQuandoContatoExistir() {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setContato("test@example.com");
        contato.setTipoContato(TipoContato.EMAIL);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        ContatoDTO contatoEncontrado = contatoService.findById(1L);

        assertNotNull(contatoEncontrado);
    }

    @Test
    void deveLancarIdNotFoundExceptionQuandoContatoNaoExistir() {
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> contatoService.findById(1L));
    }

    @Test
    void deveAtualizarContatoQuandoContatoExistir() {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setContato("novoEmail@email.com");
        contato.setTipoContato(TipoContato.EMAIL);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        ContatoDTO contatoAtualizado = contatoService.update(1L, contatoDTO);

        assertNotNull(contatoAtualizado);
    }
    @Test
    void deveDeletarContatoQuandoContatoExistir() {
        Contato contato = new Contato();
        contato.setId(1L);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        contatoService.delete(1L);

        verify(contatoRepository, times(1)).deleteById(1L);
    }
}