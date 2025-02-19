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
    public void deveRetornarExcecaoComNomeNulo() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(null);

        // Verifica que a exceção esperada é lançada
        assertThrows(CampoNotNullException.class, () -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }

    @Test
    public void deveRetornarExcecaoComNomeVazio() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("");

        assertThrows(CampoVazioException.class, () -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }

    @Test
    public void deveRetornarExcecaoComNomeInvalido() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("12345");

        assertThrows(FormatoInvalidoException.class, () -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }

    @Test
    public void deveRetornarExcecaoComCidadeInvalida() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCidade("Cidade123");

        assertThrows(FormatoInvalidoException.class, () -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }

    @Test
    public void deveRetornarExcecaoComCepInvalido() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCep("12345-6789");

        assertThrows(FormatoInvalidoException.class, () -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }

    @Test
    public void deveRetornarExcecaoComUfInvalida() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setUf("XYZ");

        assertThrows(FormatoInvalidoException.class, () -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }

    @Test
    public void deveRetornarPessoaDTOValida() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Taina");
        pessoaDTO.setCidade("São Paulo");
        pessoaDTO.setCep("12345-678");
        pessoaDTO.setUf("SP");

        // Não deve lançar exceção
        assertDoesNotThrow(() -> pessoaValidation.validarPessoaDTO(pessoaDTO));
    }
}
