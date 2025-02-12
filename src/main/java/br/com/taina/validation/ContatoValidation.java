package br.com.taina.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.taina.contatosEnum.TipoContato;
import br.com.taina.model.Contato;

public class ContatoValidation {

	/**
	 * Ver um jeito de transformar o enum em string e comparar que nem na classe pessoaValidator ... 
	 */ 
	private static final String regexCelularETelefoneFixo = "^\\(?\\d{2}\\)?\\s?9\\d{4}-\\d{4}$";
	 
	@Autowired
	TipoContato tipoContato;
	
	
	private boolean isTelefoneValido(String telefone) {
		 return !Pattern.matches(regexCelularETelefoneFixo, telefone);

	}
	
	public void validarContato(Contato contato) {
		if(isTelefoneValido(tipoContato.CELULAR)) {
			System.out.println("teste");
		}
	}
}
