package br.com.taina;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContatoValidationTest {
    @Test
    public void testContatoValido() {
        Contato contato = new Contato();
        contato.setContato("1234567890");
        contato.setTipoContato(TipoContato.CELULAR);
        
        ContatoValidation validator = new ContatoValidation();
        
        // Espera que não lance exceções
        assertDoesNotThrow(() -> validator.validarContato(contato));
    }

    @Test
    public void testContatoInvalido() {
        Contato contato = new Contato();
        contato.setContato("12345");
        contato.setTipoContato(TipoContato.CELULAR);
        
        ContatoValidation validator = new ContatoValidation();
        
        // Espera que lance uma exceção de formato inválido
        assertThrows(TelefoneFormatoInvalidoException.class, () -> validator.validarContato(contato));
    }
}
