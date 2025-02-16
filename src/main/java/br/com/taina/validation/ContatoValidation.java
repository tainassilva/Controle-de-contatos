package br.com.taina.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.enums.TipoContato;
import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.NotNullException;

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

    private boolean isContatoNulo(String contato) {
        return contato == null;
    }

    private boolean isContatoVazio(String contato) {
        return contato.trim().isEmpty();
    }

    private boolean isTipoContatoNulo(String tipoContato) {
        return tipoContato == null;
    }

    /**
     * Valida o contato sem modificar o retorno, apenas garantindo que `TipoContato` foi convertido.
     */
    public void validarContato(ContatoDTO contatoDTO) {
        if (isTipoContatoNulo(contatoDTO.getTipoContato())) {
            throw new NotNullException("Erro! O tipo de contato não pode ser nulo. Insira um tipo de contato válido: "
                    + "TELEFONE_FIXO, CELULAR, EMAIL, LINKEDIN.");
        }

        try {
            // Converte para Enum dentro da validação, sem modificar o retorno
            TipoContato.fromString(contatoDTO.getTipoContato());
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoException("Erro! Tipo de contato inválido. Insira um dos seguintes tipos: "
                    + "TELEFONE_FIXO, CELULAR, EMAIL, LINKEDIN.");
        }

        if (isContatoNulo(contatoDTO.getContato())) {
            throw new NotNullException("Erro! O contato não pode ser nulo. Insira um contato.");
        }

        if (isContatoVazio(contatoDTO.getContato())) {
            throw new CampoVazioException("Erro! O contato não pode ser vazio. Insira um contato.");
        }

        // Como o `TipoContato` já foi validado, podemos fazer a verificação do formato do contato
        switch (TipoContato.fromString(contatoDTO.getTipoContato())) {
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
