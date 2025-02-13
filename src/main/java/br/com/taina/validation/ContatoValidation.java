package br.com.taina.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.taina.contatosEnum.TipoContato;
import br.com.taina.exception.contato.TelefoneFormatoInvalidoException;
import br.com.taina.exception.contato.ContatoNuloOuVazioException;
import br.com.taina.exception.contato.EmailFormatoInvalidoException;
import br.com.taina.exception.contato.TipoContatoNuloException;
import br.com.taina.model.Contato;

@Component
public class ContatoValidation {

	/**
	 * Ver um jeito de transformar o enum em string e comparar que nem na classe pessoaValidator ... 
	 */ 
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
	private boolean isContatoNuloOuVazio(String contato) {
		return contato == null || contato.trim().isEmpty();  
	}
	   // Valida tipo de contato, se for nulo ou inválido
    private boolean isTipoContatoNulo(TipoContato tipoContato) {
        return tipoContato == null;
    }
    
	//Tipo contato null também não pode 
	public void validarContato(Contato contato) {
		
		if(isContatoNuloOuVazio(contato.getContato())) {
			throw new ContatoNuloOuVazioException("Erro! O contato não pode ser nulo ou vazio. Insira um contato.");
		}
		if(isTipoContatoNulo(contato.getTipoContato())) {
			throw new TipoContatoNuloException("Erro! O tipo de contato não pode ser nulo.");
		}
		if (contato.getTipoContato() == TipoContato.CELULAR ||  contato.getTipoContato() == TipoContato.TELEFONE_FIXO) {
		    if (isTelefoneValido(contato.getContato())) {
		        throw new TelefoneFormatoInvalidoException("Erro! Formato inválido. Insira um formato de telefone válido: 10 digitos para "
		        		+ "telefone incluindo o ddd e 11 para telefone celular");
		    }
		}
		
		if(contato.getTipoContato() == TipoContato.EMAIL) {
			if(isEmailValido(contato.getContato())) {
				throw new EmailFormatoInvalidoException("Erro! Formato inválido. Insira um email válido! Exemplo do formato : teste@email.com");
			}
		}
		
		if(contato.getTipoContato() == TipoContato.LINKEDLN) {
			if( isLinkedlnValido(contato.getContato())) {
				throw new EmailFormatoInvalidoException("Erro! O formato do LinkedIn está inválido. Use um formato correto, como: https://www.linkedin.com/in/joao-silva");
			}
		}
	}
}

	

