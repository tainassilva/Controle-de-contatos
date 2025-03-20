package br.com.taina.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.taina.dto.contato.ContatoDTO;
import br.com.taina.enums.TipoContato;
import br.com.taina.exception.FormatoInvalidoException;

/**
 * Classe responsável pela validação de dados de contato, como telefone, celular, email e linkedln
 * Realiza a verificação dos dados de entrada no DTO de contato e lança exceções personalizadas em caso de erro.
 */
@Component
public class ContatoValidation {

    
    private static final String regexCelularETelefoneFixo = "^\\d{10,11}$";

    
    private static final String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    
    private static final String regexLinkedIn = "^(https?:\\/\\/)?(www\\.)?linkedin\\.com\\/in\\/([a-zA-Z0-9-]+)$";

    
    private boolean isTelefoneValido(String telefone) {
        return !Pattern.matches(regexCelularETelefoneFixo, telefone);
    }

    
    private boolean isEmailValido(String email) {
        return !Pattern.matches(regexEmail, email);
    }

  
    private boolean isLinkedlnValido(String linkedIn) {
        return !Pattern.matches(regexLinkedIn, linkedIn);
    }

    /**
     * Método principal para validar um objeto ContatoDTO.
     * Verifica se o tipo de contato, o contato em si e o formato do contato estão válidos.
     * @param contatoDTO Objeto contendo os dados do contato a ser validado.
     */
    public void validarContato(ContatoDTO contatoDTO) {
        switch (TipoContato.valueOf(contatoDTO.getTipoContato().trim().toUpperCase())) {
            case CELULAR:
            case TELEFONE_FIXO:
                if (isTelefoneValido(contatoDTO.getContato())) {
                    throw new FormatoInvalidoException("Erro! Formato inválido. Insira um telefone válido, incluindo o DDD.");
                }
                break;

            case EMAIL:
                if (isEmailValido(contatoDTO.getContato())) {
                    throw new FormatoInvalidoException("Erro! Formato inválido. Insira um e-mail válido! Exemplo: teste@email.com");
                }
                break;

            case LINKEDIN:
                if (isLinkedlnValido(contatoDTO.getContato())) {
                    throw new FormatoInvalidoException("Erro! O formato do LinkedIn está inválido. Use um formato correto, como: www.linkedin.com/in/usuario-linkedin");
                }
                break;
        }
    }
}
