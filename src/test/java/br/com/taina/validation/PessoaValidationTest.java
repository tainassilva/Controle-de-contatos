package br.com.taina.validation;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.CampoNotNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaValidationTest {

    private PessoaValidation pessoaValidation;

    @BeforeEach
    public void setUp() {
        pessoaValidation = new PessoaValidation();
    }

    @Test
    public void testNomeNulo() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(null);

        CampoNotNullException thrown = assertThrows(CampoNotNullException.class, () -> {
            pessoaValidation.validarPessoaDTO(pessoaDTO);
        });

        assertEquals("Campo nulo não permitido! Insira um nome.", thrown.getMessage());
    }

    @Test
    public void testNomeVazio() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("");

        CampoVazioException thrown = assertThrows(CampoVazioException.class, () -> {
            pessoaValidation.validarPessoaDTO(pessoaDTO);
        });

        assertEquals("Campo vazio não permitido! Insira um nome.", thrown.getMessage());
    }

    @Test
    public void testNomeInvalido() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("12345");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            pessoaValidation.validarPessoaDTO(pessoaDTO);
        });

        assertEquals("Nome inválido! Deve conter apenas letras.", thrown.getMessage());
    }

    @Test
    public void testCidadeInvalida() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCidade("Cidade123");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            pessoaValidation.validarPessoaDTO(pessoaDTO);
        });

        assertEquals("Cidade inválida! Deve conter apenas letras.", thrown.getMessage());
    }

    @Test
    public void testCepInvalido() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCep("12345-6789");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            pessoaValidation.validarPessoaDTO(pessoaDTO);
        });

        assertEquals("CEP inválido! O formato correto é XXXXXXXX ou XXXXX-XXX.", thrown.getMessage());
    }

    @Test
    public void testUfInvalido() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setUf("XYZ");

        FormatoInvalidoException thrown = assertThrows(FormatoInvalidoException.class, () -> {
            pessoaValidation.validarPessoaDTO(pessoaDTO);
        });

        assertEquals("UF inválido! Insira um estado válido. Exemplo: SP.", thrown.getMessage());
    }

    @Test
    public void testPessoaValida() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Taina");
        pessoaDTO.setCidade("São Paulo");
        pessoaDTO.setCep("12345-678");
        pessoaDTO.setUf("SP");

        // Não deve lançar exceção
        assertDoesNotThrow(() -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }
}
