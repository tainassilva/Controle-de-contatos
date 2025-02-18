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
    void configurar() {
        MockitoAnnotations.openMocks(this);

        pessoa = new Pessoa();
        pessoa.setId(1L);

        contatoDTO = new ContatoDTO();
        contatoDTO.setIdPessoa(1L);
        contatoDTO.setTipoContato("EMAIL");
        contatoDTO.setContato("test@example.com");
    }

    @Test
    void salvar_DeveSalvarContato_QuandoPessoaExistir() {
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
    void salvar_DeveLancarIdNotFoundException_QuandoPessoaNaoExistir() {
        // Simula o comportamento dos métodos dos repositórios
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        // Executa o método do serviço e verifica se a exceção é lançada
        assertThrows(IdNotFoundException.class, () -> contatoService.save(contatoDTO));
    }

    @Test
    void buscarPorId_DeveRetornarContatoDTO_QuandoContatoExistir() {
        // Simula o comportamento dos métodos dos repositórios
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setContato("test@example.com");
        contato.setTipoContato(TipoContato.EMAIL);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        // Executa o método do serviço
        ContatoDTO contatoEncontrado = contatoService.findById(1L);

        // Verifica os dados do contato retornado
        assertNotNull(contatoEncontrado);
        assertEquals("test@example.com", contatoEncontrado.getContato());
    }

    @Test
    void buscarPorId_DeveLancarIdNotFoundException_QuandoContatoNaoExistir() {
        // Simula o comportamento dos métodos dos repositórios
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        // Executa o método do serviço e verifica se a exceção é lançada
        assertThrows(IdNotFoundException.class, () -> contatoService.findById(1L));
    }

    @Test
    void atualizar_DeveAtualizarContato_QuandoContatoExistir() {
        // Simula o comportamento dos métodos dos repositórios
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setContato("old@example.com");
        contato.setTipoContato(TipoContato.EMAIL);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        // Executa o método do serviço
        ContatoDTO contatoAtualizado = contatoService.update(1L, contatoDTO);

        // Verifica os dados do contato atualizado
        assertNotNull(contatoAtualizado);
        assertEquals("test@example.com", contatoAtualizado.getContato());
    }

    @Test
    void atualizar_DeveLancarIdNotFoundException_QuandoContatoNaoExistir() {
        // Simula o comportamento dos métodos dos repositórios
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        // Executa o método do serviço e verifica se a exceção é lançada
        assertThrows(IdNotFoundException.class, () -> contatoService.update(1L, contatoDTO));
    }

    @Test
    void deletar_DeveDeletarContato_QuandoContatoExistir() {
        // Simula o comportamento dos métodos dos repositórios
        Contato contato = new Contato();
        contato.setId(1L);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        // Executa o método do serviço
        contatoService.delete(1L);

        // Verifica se o método de deleção foi chamado
        verify(contatoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletar_DeveLancarIdNotFoundException_QuandoContatoNaoExistir() {
        // Simula o comportamento dos métodos dos repositórios
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        // Executa o método do serviço e verifica se a exceção é lançada
        assertThrows(IdNotFoundException.class, () -> contatoService.delete(1L));
    }
}
