package br.com.taina.validation;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.CampoNotNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContatoValidationTest {

    private ContatoValidation contatoValidation;

    @BeforeEach
    public void setUp() {
        contatoValidation = new ContatoValidation();
    }

    @Test
    public void testTipoContatoNulo() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato(null);
        contatoDTO.setContato("1234567890");

        CampoNotNullException thrown = assertThrows(CampoNotNullException.class, () -> {
            contatoValidation.validarContato(contatoDTO);
        });

        assertEquals("Erro! O tipo de contato não pode ser nulo. Insira um tipo de contato válido: TELEFONE_FIXO, CELULAR, EMAIL, LINKEDIN.", thrown.getMessage());
    }

    @Test
    public void testContatoNulo() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("CELULAR");
        contatoDTO.setContato(null);

        CampoNotNullException thrown = assertThrows(CampoNotNullException.class, () -> {
            contatoValidation.validarContato(contatoDTO);
        });

        assertEquals("Erro! O contato não pode ser nulo. Insira um contato.", thrown.getMessage());
    }

    @Test
    public void testContatoVazio() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("CELULAR");
        contatoDTO.setContato(" ");

        CampoVazioException thrown = assertThrows(CampoVazioException.class, () -> {
            contatoValidation.validarContato(contatoDTO);
        });

        assertEquals("Erro! O contato não pode ser vazio. Insira um contato.", thrown.getMessage());
    }

    @Test
    public void testTipoContatoInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("INVALIDO");
        contatoDTO.setContato("1234567890");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            contatoValidation.validarContato(contatoDTO);
        });

        assertEquals("Erro! Tipo de contato inválido. Insira um dos seguintes tipos: TELEFONE_FIXO, CELULAR, EMAIL, LINKEDIN.", thrown.getMessage());
    }

    @Test
    public void testTelefoneInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("CELULAR");
        contatoDTO.setContato("12345");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            contatoValidation.validarContato(contatoDTO);
        });

        assertEquals("Erro! Formato inválido. Insira um telefone válido, incluindo o DDD.", thrown.getMessage());
    }

    @Test
    public void testEmailInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("EMAIL");
        contatoDTO.setContato("invalid-email");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            contatoValidation.validarContato(contatoDTO);
        });

        assertEquals("Erro! Formato inválido. Insira um e-mail válido! Exemplo: teste@email.com", thrown.getMessage());
    }

    @Test
    public void testLinkedinInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("LINKEDIN");
        contatoDTO.setContato("www.linkedin.com/usuario");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            contatoValidation.validarContato(contatoDTO);
        });

        assertEquals("Erro! O formato do LinkedIn está inválido. Use um formato correto, como: www.linkedin.com/in/usuario-linkedin", thrown.getMessage());
    }

    @Test
    public void testContatoValido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("CELULAR");
        contatoDTO.setContato("11987654321");

        // Não deve lançar exceção
        assertDoesNotThrow(() -> contatoValidation.validarContato(contatoDTO));
    }
}
