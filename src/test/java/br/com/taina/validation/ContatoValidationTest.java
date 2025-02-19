package br.com.taina.validation;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.CampoNotNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContatoValidationTest {

    private ContatoValidation contatoValidation;

    @BeforeEach
    void setup() {
        contatoValidation = new ContatoValidation();
    }

    @Test
    void deveRetornarExcecaoContatoNulo() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato(null);
        contatoDTO.setContato("1234567890");

        // Verifica que a exceção esperada é lançada
        assertThrows(CampoNotNullException.class, () -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarExcecaoTipoContatoNulo() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("CELULAR");
        contatoDTO.setContato(null);

        assertThrows(CampoNotNullException.class, () -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarExcecaoComContatoVazio() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("EMAIL");
        contatoDTO.setContato("");

        assertThrows(CampoVazioException.class, () -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarExcecaoComTipoContatoInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("INVALIDO");
        contatoDTO.setContato("teste@email.com");

        assertThrows(FormatoInvalidoException.class, () -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarOkContatoTelefoneValido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("CELULAR");
        contatoDTO.setContato("1234567890");

        // Não esperamos exceção aqui
        assertDoesNotThrow(() -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarExcecaoComTelefoneInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("CELULAR");
        contatoDTO.setContato("12345");

        assertThrows(FormatoInvalidoException.class, () -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarOkEmailValido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("EMAIL");
        contatoDTO.setContato("teste@email.com");

        assertDoesNotThrow(() -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarOkContatoEmailInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("EMAIL");
        contatoDTO.setContato("teste@email");

        assertThrows(FormatoInvalidoException.class, () -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void deveRetornarOkContatoLinkedlnValido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("LINKEDIN");
        contatoDTO.setContato("www.linkedin.com/in/usuario-linkedin");

        assertDoesNotThrow(() -> contatoValidation.validarContato(contatoDTO));
    }

    @Test
    void tdeveRetornarExcecaoComContatoLinkedInInvalido() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipoContato("LINKEDIN");
        contatoDTO.setContato("www.linkedicccccn.com/usuario-linkedin");

        assertThrows(FormatoInvalidoException.class, () -> contatoValidation.validarContato(contatoDTO));
    }
}
